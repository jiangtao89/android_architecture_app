package com.jt.funny.router;

/**
 * Target
 */
public class Target {
    private Class mClass;
    private IRouteHandler mRouteListener;

    public Target(Class aClass) {
        mClass = aClass;
    }

    public Target(IRouteHandler routeListener) {
        mRouteListener = routeListener;
    }

    public Class getClss() {
        return mClass;
    }

    public IRouteHandler getRouteListener() {
        return mRouteListener;
    }
}
