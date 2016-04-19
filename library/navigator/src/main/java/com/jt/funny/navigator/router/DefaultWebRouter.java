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
public class DefaultWebRouter extends Router {

    @Override
    public boolean open(@NonNull Route route, @NonNull Target target) {
        IRouteHandler routeHandler = target.getRouteHandler();
        if (routeHandler != null) {
            return routeHandler.open(route, target);
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
