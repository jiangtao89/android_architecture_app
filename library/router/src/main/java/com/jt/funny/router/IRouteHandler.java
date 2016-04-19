package com.jt.funny.router;

/**
 * Created by jiangtao on 16/3/27.
 *
 * @author jiangtao
 * @version 1.0.0
 */
public interface IRouteHandler {

    /**
     * open route
     *
     * @param route        route
     * @param routeManager routeManager
     */
    public boolean open(Route route, RouteManager routeManager);
}
