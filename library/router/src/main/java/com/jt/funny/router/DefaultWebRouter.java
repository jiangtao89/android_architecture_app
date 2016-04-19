package com.jt.funny.router;

import android.content.Intent;

/**
 * Created by jiangtao on 16/3/27.
 *
 * @author jiangtao
 * @version 1.0.0
 */
public class DefaultWebRouter extends Router {

    @Override
    public boolean open(Route route, RouteManager routeManager) {

        String uri = RouterUtils.getURL(route.getUri());
        if (RouterUtils.isEmpty(uri)) {
            return false;
        }

        Router.Target target = routeManager.getTarget(uri);
        if (target != null) {
            final IRouteHandler routeListener = target.getRouteListener();
            if (routeListener != null) {
                return routeListener.open(route);
            }
        }

        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(route.getUri());
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | route.getFlags());
            getContext().startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
