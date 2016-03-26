package com.jt.funny.framework.core.service;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.os.Bundle;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.LinkedList;

/**
 * Created by jiangtao on 16/3/23.
 *
 * @author jiangtao
 * @version 1.0.0
 */
@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
public class ActivityStackManagerService implements Application.ActivityLifecycleCallbacks {

    private static ActivityStackManagerService sInstance;

    private LinkedList<Activity> mActivities = new LinkedList<Activity>();

    /**
     * getInstance
     *
     * @return ActivityStackManagerService
     */
    public static ActivityStackManagerService getInstance() {
        synchronized (ActivityStackManagerService.class) {
            if (sInstance == null) {
                sInstance = new ActivityStackManagerService();
            }
            return sInstance;
        }
    }


    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        if (!mActivities.contains(activity)) {
            mActivities.addFirst(activity);
        }
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        mActivities.remove(activity);
    }

    /**
     * get top activity
     *
     * @return top activity
     */
    public Activity getTopActivity() {
        if (mActivities.size() > 0) {
            return mActivities.getFirst();
        }
        return null;
    }

    /**
     * dump activity stack
     *
     * @return activities stack information
     */
    public String dump() {
        StringBuilder builder = new StringBuilder();
        for (Activity activity : mActivities) {
            if (activity != null) {
                String simpleName = activity.getClass().getName();
                builder.append(simpleName);
                StringWriter wr = new StringWriter();
                activity.dump("[" + simpleName + "]", null, new PrintWriter(wr), null);
                builder.append(wr.toString());
                builder.append('\n');
                builder.append('\n');
            }
        }
        return builder.toString();
    }
}
