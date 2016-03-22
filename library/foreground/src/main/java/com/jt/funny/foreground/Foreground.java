package com.jt.funny.foreground;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.os.*;

import java.util.LinkedList;

/**
 * Created by jiangtao on 16/3/22.
 * <p/>
 * 前后台变化
 *
 * @author jiangtao
 * @version 1.0.0
 */
@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
public class Foreground implements Application.ActivityLifecycleCallbacks {

    private static final int DELAY_MILLIS = 300;

    private boolean mForeground = true;
    private boolean mPaused = false;
    private ForegroundHandler mHandler;
    private static Foreground sInstance;

    private LinkedList<OnForegroundChangeListener> mListeners;

    /**
     * Foreground
     *
     * @return Foreground
     */
    public static Foreground getInstance() {
        synchronized (Foreground.class) {
            if (sInstance == null) {
                sInstance = new Foreground();
            }
        }
        return sInstance;
    }

    private Foreground() {
        mHandler = new ForegroundHandler(Looper.getMainLooper());
        mListeners = new LinkedList<OnForegroundChangeListener>();
    }

    /**
     * 是否处于前台
     *
     * @return 是否处于前台
     */
    public boolean isForeground() {
        return mForeground;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {

    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {
        mPaused = false;
        mHandler.removeMessages(MSG_PAUSED);
        if (!mForeground) {
            mForeground = true;
            dispatch(mForeground);
        }
    }

    @Override
    public void onActivityPaused(Activity activity) {
        mPaused = true;
        mHandler.removeMessages(MSG_PAUSED);
        mHandler.sendEmptyMessageDelayed(MSG_PAUSED, DELAY_MILLIS);
    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }

    /**
     * 检查 msg
     */
    private static final int MSG_PAUSED = 1;

    /**
     * foreground handler
     */
    private class ForegroundHandler extends Handler {
        /**
         * ForegroundHandler
         *
         * @param looper looper
         */
        public ForegroundHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (MSG_PAUSED == msg.what) {
                if (mForeground && mPaused) {
                    mForeground = false;
                    dispatch(mForeground);
                }
            }
        }
    }

    /**
     * 注册监听前后台变化
     *
     * @param listener listener
     */
    public void registerListener(OnForegroundChangeListener listener) {
        if (listener == null) {
            return;
        }
        synchronized (Foreground.class) {
            if (!mListeners.contains(listener)) {
                mListeners.add(listener);
            }
        }
    }

    /**
     * 取消注册
     *
     * @param listener listener
     */
    public void unregisterListener(OnForegroundChangeListener listener) {
        if (listener == null) {
            return;
        }
        synchronized (Foreground.class) {
            mListeners.remove(listener);
        }
    }

    /**
     * 分发
     *
     * @param foreground foreground
     */
    private void dispatch(boolean foreground) {
        synchronized (Foreground.class) {
            for (OnForegroundChangeListener listener : mListeners) {
                if (foreground) {
                    listener.onSwitchForeground();
                } else {
                    listener.onSwitchBackground();
                }
            }
        }
    }

    /**
     * OnForegroundChangeListener
     */
    public static interface OnForegroundChangeListener {

        /**
         * onSwitchForeground
         */
        public void onSwitchForeground();

        /**
         * onSwitchBackground
         */
        public void onSwitchBackground();
    }

}
