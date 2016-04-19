package com.jt.funny.router;

import android.app.Activity;
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
        String uri = RouterUtils.decodeWithoutQuery(route.getUri());
        if (RouterUtils.isEmpty(uri)) {
            return false;
        }

        Target target = routeManager.getTarget(uri);
        if (target == null) {
            return false;
        }

        try {
            final IRouteHandler routeHandler = target.getRouteListener();
            if (routeHandler != null) {
                return routeHandler.open(route);
            }

            final Class cls = target.getClss();
            if (cls == null) {
                return false;
            }

            Activity activity = route.getActivity();
            if (activity == null) {
                return false;
            }

            Intent intent = new Intent(activity, cls);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | route.getFlags());
            intent.setData(route.getUri());

            int requestCode = route.getRequestCode();
            if (requestCode == 0) {
                activity.startActivity(intent);
            } else {
                activity.startActivityForResult(intent, requestCode);
            }

            int enterAnim = route.getEnterAnim();
            int exitAnim = route.getExitAnim();
            if (enterAnim != 0 || exitAnim != 0) {
                activity.overridePendingTransition(enterAnim, exitAnim);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
