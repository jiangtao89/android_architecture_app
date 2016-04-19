package com.jt.funny.navigator;

import android.net.Uri;
import android.support.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jiangtao on 16/3/27.
 *
 * @author jiangtao
 * @version 1.0.0
 */
public class Route {

    private Uri mUri;
    private int mFlags;
    private int mEnterAnim;
    private int mExitAnim;

    public Uri getUri() {
        return mUri;
    }

    void setUri(Uri uri) {
        mUri = uri;
    }

    public int getFlags() {
        return mFlags;
    }

    void setFlags(int flags) {
        mFlags = flags;
    }

    public int getEnterAnim() {
        return mEnterAnim;
    }

    void setEnterAnim(int enterAnim) {
        mEnterAnim = enterAnim;
    }

    public int getExitAnim() {
        return mExitAnim;
    }

    void setExitAnim(int exitAnimation) {
        mExitAnim = exitAnimation;
    }

    /**
     * open
     *
     * @return open success or failed
     */
    public boolean open() {
        return Routers.getInstances().open(this);
    }

    public static class Builder {

        private Uri mUri;
        private int mFlags;
        private int mEnterAnim;
        private int mExitAnim;
        private HashMap<String, String> mQueries = new HashMap<String, String>();

        public Builder() {

        }

        public Builder withUrl(@NonNull String uri) {
            mUri = Uri.parse(uri);
            return this;
        }

        public Builder withUrl(@NonNull Uri uri) {
            mUri = uri;
            return this;
        }

        public Builder addParameter(@NonNull String key, @NonNull String value) {
            mQueries.put(key, value);
            return this;
        }

        public Builder addParameter(@NonNull String key, @NonNull Number value) {
            mQueries.put(key, value.toString());
            return this;
        }

        public Builder addParameter(@NonNull String key, @NonNull Boolean value) {
            mQueries.put(key, value.toString());
            return this;
        }

        /**
         * flags
         * <p>
         * {@link android.content.Intent#addFlags(int)}
         *
         * @param flags flags
         * @return this
         */
        public Builder addFlags(int flags) {
            mFlags = flags;
            return this;
        }

        /**
         * enterAnim
         * <p>
         * {@link android.app.Activity#overridePendingTransition(int, int)}
         *
         * @param anim anim
         * @return
         */
        public Builder enterAnim(int anim) {
            mEnterAnim = anim;
            return this;
        }

        /**
         * exitAnim
         * <p>
         * {@link android.app.Activity#overridePendingTransition(int, int)}
         *
         * @param anim anim
         * @return
         */
        public Builder exitAnim(int anim) {
            mExitAnim = anim;
            return this;
        }

        public Route build() {
            if (mUri == null) {
                if (Routers.isDebug()) {
                    throw new IllegalArgumentException("uri is can't null");
                }
                return new Route();
            }
            Route route = new Route();
            Uri.Builder builder = mUri.buildUpon();
            for (Map.Entry<String, String> entry : mQueries.entrySet()) {
                builder.appendQueryParameter(entry.getKey(), entry.getValue());
            }
            route.setUri(builder.build());
            route.setFlags(mFlags);
            route.setEnterAnim(mEnterAnim);
            route.setExitAnim(mExitAnim);
            return route;
        }
    }

}
