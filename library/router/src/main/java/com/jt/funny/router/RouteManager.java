package com.jt.funny.router;

import android.app.Activity;
import android.support.annotation.NonNull;

import java.util.HashMap;

/**
 * Created by jiangtao on 16/4/19.
 *
 * @author jiang.tao
 * @version 1.0.0
 */
class RouteManager {

    private boolean isDebug = false;

    private HashMap<String, Router.Target> mRoutes = new HashMap<String, Router.Target>(8);

    public void setDebug(boolean debug) {
        isDebug = debug;
    }

    /**
     * @param uri  uri
     * @param clss clss
     */
    public void registerRoute(@NonNull String uri, @NonNull Class<? extends Activity> clss) {
        if (RouterUtils.isEmptySchema(uri)) {
            if (isDebug) {
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
     * @param routeListener routeListener
     */
    public void registerRoute(@NonNull String uri, @NonNull IRouteListener routeListener) {
        if (RouterUtils.isEmptySchema(uri)) {
            if (isDebug) {
                throw new IllegalArgumentException("uri format error!");
            }
            return;
        }
        Router.Target target = new Router.Target(routeListener);
        int queryIndex = uri.indexOf('?');
        if (queryIndex > 0) {
            mRoutes.put(uri.substring(0, queryIndex), target);
        } else {
            mRoutes.put(uri, target);
        }
    }

    public Router.Target getTarget(String uri) {
        return mRoutes.get(uri);
    }
}
