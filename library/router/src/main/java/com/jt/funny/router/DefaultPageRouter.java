package com.jt.funny.router;

import android.content.Intent;

/**
 * Created by jiangtao on 16/3/27.
 *
 * @author jiangtao
 * @version 1.0.0
 */
public class DefaultPageRouter extends Router {

    @Override
    public boolean open(Route route, RouteManager routeManager) {

        String uri = RouterUtils.getURL(route.getUri());
        if (RouterUtils.isEmpty(uri)) {
            return false;
        }

        Router.Target target = routeManager.getTarget(uri);
        if (target == null) {
            return false;
        }

        final Class cls = target.getClss();
        final IRouteListener routeListener = target.getRouteListener();
        try {
            if (routeListener != null) {
                return routeListener.open(route);
            }
            if (cls != null) {
                Intent intent = new Intent(getContext(), cls);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | route.getFlags());
                intent.setData(route.getUri());
                getContext().startActivity(intent);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
