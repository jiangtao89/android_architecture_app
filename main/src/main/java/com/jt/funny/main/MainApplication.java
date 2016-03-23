package com.jt.funny.main;

import android.app.Application;
import com.jt.funny.foreground.ActivityStackManager;
import com.jt.funny.foreground.Foreground;

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
        registerActivityLifecycleCallbacks(Foreground.getInstance());

        /** register activity stack manager */
        registerActivityLifecycleCallbacks(ActivityStackManager.getInstance());
    }

    @Override
    public void onTerminate() {
        super.onTerminate();

        /** unregister foreground and background switch **/
        unregisterActivityLifecycleCallbacks(Foreground.getInstance());

        /** unregister activity stack manager */
        unregisterActivityLifecycleCallbacks(ActivityStackManager.getInstance());
    }

}
