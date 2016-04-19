package com.jt.funny.navigator;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;

import java.util.HashMap;

/**
 * Created by jiangtao on 16/3/26.
 *
 * @author jiangtao
 * @version 1.0.0
 */
public class Routers {

    private Context mContext;
    private HashMap<String, Router> mRouters = new HashMap<String, Router>(4);
    private HashMap<String, Router.Target> mRoutes = new HashMap<String, Router.Target>(8);
    private static volatile Routers sSingleton = null;

    private static boolean sDebug = true;

    public static Routers getInstances() {
        if (sSingleton == null) {
            synchronized (Routers.class) {
                if (sSingleton == null) {
                    sSingleton = new Routers();
                }
            }
        }
        return sSingleton;
    }
//
//    /**
//     * Set the global instance returned from {@link #getInstances}.
//     * <p>
//     * This method must be called before any calls to {@link #getInstances} and may only be called once.
//     */
//    public static void setSingletonInstance(Routers routers) {
//        if (routers == null) {
//            throw new IllegalArgumentException("routers must not be null.");
//        }
//        synchronized (Routers.class) {
//            if (sSingleton != null) {
//                throw new IllegalStateException("Singleton instance already exists.");
//            }
//            sSingleton = routers;
//        }
//    }

    private Routers() {

    }

    /**
     * debug
     *
     * @param debug debug
     */
    public void setDebug(boolean debug) {
        sDebug = debug;
    }

    /**
     *
     * @return
     */
    public static boolean isDebug() {
        return sDebug;
    }

    /**
     * set context
     *
     * @param context context
     */
    public void setContext(Context context) {
        mContext = context;
    }

    Context getContext() {
        return mContext;
    }

    /**
     * registerRouter
     *
     * @param scheme scheme
     * @param router router class
     */
    public void registerRouter(@NonNull String scheme, @NonNull Router router) {
        mRouters.put(scheme, router);
        router.setContext(getContext());
    }

    /**
     * @param uri  uri
     * @param clss clss
     */
    public void registerRoute(@NonNull String uri, @NonNull Class<? extends Activity> clss) {
        if (isEmptySchema(uri)) {
            if (sDebug) {
                throw new IllegalArgumentException("uri format error!");
            }
            return;
        }
        Router.Target target = new Router.Target(clss);
        int queryIndex = uri.indexOf('?');
        if (queryIndex > 0) {
            mRoutes.put(uri.substring(0, queryIndex), target);
        } else {
            mRoutes.put(uri, target);
        }
    }

    /**
     * @param uri          uri
     * @param routeHandler routeHandler
     */
    public void registerRoute(@NonNull String uri, @NonNull IRouteHandler routeHandler) {
        if (isEmptySchema(uri)) {
            if (sDebug) {
                throw new IllegalArgumentException("uri format error!");
            }
            return;
        }
        Router.Target target = new Router.Target(routeHandler);
        int queryIndex = uri.indexOf('?');
        if (queryIndex > 0) {
            mRoutes.put(uri.substring(0, queryIndex), target);
        } else {
            mRoutes.put(uri, target);
        }
    }

    private boolean isEmptySchema(@NonNull String uri) {
        Uri URI = Uri.parse(uri);
        String schema = URI.getScheme();
        if (StringUtils.isEmpty(schema)) {
            return true;
        }
        return false;
    }

    private Router getRouter(@NonNull String scheme) {
        return mRouters.get(scheme);
    }

    Router.Target getTarget(String uri) {
        return mRoutes.get(uri);
    }

    /**
     * open route
     *
     * @param route route
     * @return open success or failed
     */
    boolean open(@NonNull Route route) {
        Uri URI = route.getUri();
        if (URI == null || StringUtils.isEmpty(URI.getScheme())) {
            return false;
        }

        Router router = getRouter(URI.getScheme());
        if (router == null) {
            return false;
        }

        String uri = URI.toString();
        int queryIndex = uri.indexOf('?');
        if (queryIndex > 0) {
            uri = uri.substring(0, queryIndex);
        }

        Router.Target target = getTarget(uri);
        if (target == null) {
            return false;
        }
        return router.open(route, target);
    }

//    /**
//     * Builder
//     */
//    public final static class Builder {
//
//        private Context mContext;
//        private boolean mDebug = true;
//        private HashMap<String, Router> mRouters = new HashMap<String, Router>();
//
//        /**
//         * Builder
//         */
//        public Builder() {
//        }
//
//        /**
//         * context
//         *
//         * @param context context
//         * @return Builder
//         */
//        public Builder context(@NonNull Context context) {
//            mContext = context;
//            return this;
//        }
//
//        /**
//         * registerRouter
//         *
//         * @param schema schema
//         * @param router router
//         * @return this
//         */
//        public Builder registerRouter(@NonNull String schema, @NonNull Router router) {
//            mRouters.put(schema, router);
//            return this;
//        }
//
//        /**
//         * debug
//         *
//         * @param debug debug
//         * @return this
//         */
//        public Builder debug(boolean debug) {
//            mDebug = debug;
//            return this;
//        }
//
//        /**
//         * build
//         *
//         * @return Routers
//         */
//        public Routers build() {
//            Routers routers = new Routers();
//            routers.setContext(mContext.getApplicationContext());
//            routers.setDebug(mDebug);
//            for (Map.Entry<String, Router> entry : mRouters.entrySet()) {
//                routers.registerRoute(entry.getKey(), entry.getValue());
//            }
//            return routers;
//        }
//    }
}
