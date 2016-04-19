package com.jt.funny.router;

import android.support.annotation.NonNull;

import java.util.HashMap;

/**
 * Created by jiangtao on 16/4/19.
 *
 * @author jiang.tao
 * @version 1.0.0
 */
class RouterManager {

    private HashMap<String, Router> mRouters = new HashMap<String, Router>(4);

    private boolean isDebug = false;

    /**
     * set debug
     *
     * @param debug debug
     */
    public void setDebug(boolean debug) {
        isDebug = debug;
    }

    /**
     * registerRouter
     *
     * @param scheme scheme
     * @param router router class
     */
    public void registerRouter(@NonNull String scheme, @NonNull Router router) {
        if (RouterUtils.isEmpty(scheme)) {
            if (isDebug) {
                throw new IllegalArgumentException("schema is empty!");
            }
            return;
        }
        mRouters.put(scheme, router);
    }

    /**
     * getRouter
     *
     * @param scheme scheme
     * @return Router
     */
    public Router getRouter(@NonNull String scheme) {
        return mRouters.get(scheme);
    }

}
