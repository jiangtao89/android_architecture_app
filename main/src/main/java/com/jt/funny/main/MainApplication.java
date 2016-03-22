package com.jt.funny.main;

import android.app.Application;
import com.jt.funny.foreground.Foreground;

/**
 * Created by jiangtao on 16/3/22.
 *
 * @author goddard.jt
 * @version 1.0.0
 */
public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Foreground.getInstance().registerActivityLifecycle(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();

        Foreground.getInstance().unregisterActivityLifecycle(this);
    }
}
