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

    private HashMap<String, Target> mRoutes = new HashMap<String, Target>(8);

    public void setDebug(boolean debug) {
        isDebug = debug;
    }

    /**
     * @param uri  uri
     * @param clss clss
     */
    public void registerRoute(@NonNull String uri, @NonNull Class<? extends Activity> clss) {
        if (isEmptySchema(uri)) {
            return;
        }
        Target target = new Target(clss);
        mRoutes.put(RouterUtils.decodeWithoutQuery(uri), target);
    }

    /**
     * @param uri           uri
     * @param routeListener routeListener
     */
    public void registerRoute(@NonNull String uri, @NonNull IRouteHandler routeListener) {
        if (isEmptySchema(uri)) {
            return;
        }
        Target target = new Target(routeListener);
        mRoutes.put(RouterUtils.decodeWithoutQuery(uri), target);
    }

    public Target getTarget(String uri) {
        return mRoutes.get(uri);
    }

    private boolean isEmptySchema(@NonNull String uri) {
        if (RouterUtils.isEmptySchema(uri)) {
            if (isDebug) {
                throw new IllegalArgumentException("uri format error!");
            }
            return true;
        }
        return false;
    }
}
