package com.jt.funny.main;

import android.app.Application;
import com.jt.funny.framework.core.service.ActivityStackManagerService;
import com.jt.funny.framework.core.service.ForegroundService;

/**
 * Created by jiangtao on 16/3/22.
 *
 * @author jiangtao
 * @version 1.0.0
 */
public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        /** register foreground and background switch **/
        registerActivityLifecycleCallbacks(ForegroundService.getInstance());

        /** register activity stack manager */
        registerActivityLifecycleCallbacks(ActivityStackManagerService.getInstance());
    }

    @Override
    public void onTerminate() {
        super.onTerminate();

        /** unregister foreground and background switch **/
        unregisterActivityLifecycleCallbacks(ForegroundService.getInstance());

        /** unregister activity stack manager */
        unregisterActivityLifecycleCallbacks(ActivityStackManagerService.getInstance());
    }

}
