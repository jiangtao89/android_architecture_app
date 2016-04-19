package com.jt.funny.router;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;

/**
 * Created by jiangtao on 16/3/26.
 *
 * @author jiangtao
 * @version 1.0.0
 */
public class Routers {

    private Context mContext;
    private static boolean sDebug = true;

    private final RouteManager mRouteManager;
    private final RouterManager mRouterManager;

    private static volatile Routers sSingleton = null;

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

    private Routers() {
        mRouteManager = new RouteManager();
        mRouterManager = new RouterManager();
    }

    /**
     * debug
     *
     * @param debug debug
     */
    public void setDebug(boolean debug) {
        sDebug = debug;
        mRouteManager.setDebug(debug);
        mRouterManager.setDebug(debug);
    }

    /**
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
        mRouterManager.registerRouter(scheme, router);
    }

    /**
     * registerRoute
     *
     * @param uri  uri
     * @param clss clss
     */
    public void registerRoute(@NonNull String uri, @NonNull Class<? extends Activity> clss) {
        mRouteManager.registerRoute(uri, clss);
    }

    /**
     * registerRoute
     *
     * @param uri          uri
     * @param routeHandler routeHandler
     */
    public void registerRoute(@NonNull String uri, @NonNull IRouteListener routeHandler) {
        mRouteManager.registerRoute(uri, routeHandler);
    }

    /**
     * open route
     *
     * @param route route
     * @return open success or failed
     */
    boolean open(@NonNull Route route) {
        Uri URI = route.getUri();
        if (RouterUtils.isEmptySchema(URI)) {
            return false;
        }

        Router router = mRouterManager.getRouter(URI.getScheme());
        if (router == null) {
            return false;
        }

        router.setContext(getContext());
        return router.open(route, mRouteManager);
    }
}
