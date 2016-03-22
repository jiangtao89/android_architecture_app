package com.jt.funny.main;

import android.app.Application;
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

        /** for foreground and background switch **/
        registerActivityLifecycleCallbacks(Foreground.getInstance());
    }

    @Override
    public void onTerminate() {
        super.onTerminate();

        unregisterActivityLifecycleCallbacks(Foreground.getInstance());
    }

}
