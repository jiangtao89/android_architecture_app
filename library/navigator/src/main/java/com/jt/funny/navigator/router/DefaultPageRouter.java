package com.jt.funny.navigator.router;

import android.content.Intent;
import android.support.annotation.NonNull;
import com.jt.funny.navigator.IRouteHandler;
import com.jt.funny.navigator.Route;
import com.jt.funny.navigator.Router;

/**
 * Created by jiangtao on 16/3/27.
 *
 * @author jiangtao
 * @version 1.0.0
 */
public class DefaultPageRouter extends Router {

    @Override
    public boolean open(@NonNull Route route, @NonNull Target target) {
        final Class cls = target.getClss();
        final IRouteHandler routeHandler = target.getRouteHandler();
        try {
            if (routeHandler != null) {
                return routeHandler.open(route, target);
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
