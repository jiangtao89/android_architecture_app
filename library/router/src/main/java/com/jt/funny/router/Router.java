package com.jt.funny.router;

import android.content.Context;

/**
 * Created by jiangtao on 16/3/27.
 *
 * @author jiangtao
 * @version 1.0.0
 */
public abstract class Router implements IRouteHandler {

    private Context mContext;

    /**
     * set context
     *
     * @param context context
     */
    public void setContext(Context context) {
        mContext = context;
    }

    /**
     * get context
     *
     * @return context
     */
    public Context getContext() {
        return mContext;
    }

    /**
     * Target
     */
    public static class Target {
        private Class mClass;
        private IRouteHandler mRouteHandler;

        public Target(Class aClass) {
            mClass = aClass;
        }

        public Target(IRouteHandler routeHandler) {
            mRouteHandler = routeHandler;
        }

        public Class getClss() {
            return mClass;
        }

        public IRouteHandler getRouteHandler() {
            return mRouteHandler;
        }
    }
}
