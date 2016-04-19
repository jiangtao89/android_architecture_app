package com.jt.funny.router;

import android.support.annotation.NonNull;

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
     * @param route route
     */
    public boolean open(@NonNull Route route, @NonNull Router.Target target);
}
