package com.jt.funny.router;

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

    /**
     * Helper class for building or manipulating URI references. Not safe for
     * concurrent use.
     * <p>
     * <p>An absolute hierarchical URI reference follows the pattern:
     * {@code <scheme>://<authority><absolute path>?<query>#<fragment>}
     * <p>
     * <p>Relative URI references (which are always hierarchical) follow one
     * of two patterns: {@code <relative or absolute path>?<query>#<fragment>}
     * or {@code //<authority><absolute path>?<query>#<fragment>}
     * <p>
     * <p>An opaque URI follows this pattern:
     * {@code <scheme>:<opaque part>#<fragment>}
     * <p>
     * <p>Use {@link Uri#buildUpon()} to obtain a builder representing an existing URI.
     */
    public static final class Builder {

        private int mFlags;
        private int mEnterAnim;
        private int mExitAnim;

        Uri.Builder mBuilder = new Uri.Builder();

        /**
         * Constructs a new Builder.
         */
        public Builder() {
        }

        /**
         * Sets the scheme.
         *
         * @param scheme name or {@code null} if this is a relative Uri
         */
        public Builder scheme(String scheme) {
            mBuilder.scheme(scheme);
            return this;
        }

        /**
         * Encodes and sets the authority.
         */
        public Builder authority(String authority) {
            mBuilder.authority(authority);
            return this;
        }

        /**
         * Sets the previously encoded authority.
         */
        public Builder encodedAuthority(String authority) {
            mBuilder.encodedAuthority(authority);
            return this;
        }

        /**
         * Sets the path. Leaves '/' characters intact but encodes others as
         * necessary.
         * <p>
         * <p>If the path is not null and doesn't start with a '/', and if
         * you specify a scheme and/or authority, the builder will prepend the
         * given path with a '/'.
         */
        public Builder path(String path) {
            mBuilder.path(path);
            return this;
        }

        /**
         * Sets the previously encoded path.
         * <p>
         * <p>If the path is not null and doesn't start with a '/', and if
         * you specify a scheme and/or authority, the builder will prepend the
         * given path with a '/'.
         */
        public Builder encodedPath(String path) {
            mBuilder.encodedPath(path);
            return this;
        }

        /**
         * Encodes the given segment and appends it to the path.
         */
        public Builder appendPath(String newSegment) {
            mBuilder.appendPath(newSegment);
            return this;
        }

        /**
         * Appends the given segment to the path.
         */
        public Builder appendEncodedPath(String newSegment) {
            mBuilder.appendEncodedPath(newSegment);
            return this;
        }

        /**
         * Encodes and sets the query.
         */
        public Builder query(String query) {
            mBuilder.query(query);
            return this;
        }

        /**
         * Encodes the key and value and then appends the parameter to the
         * query string.
         *
         * @param key   which will be encoded
         * @param value which will be encoded
         */
        public Builder appendQueryParameter(@NonNull String key, @NonNull String value) {
            mBuilder.appendQueryParameter(key, value);
            return this;
        }

        /**
         * Encodes the key and value and then appends the parameter to the
         * query string.
         *
         * @param key   which will be encoded
         * @param value which will be encoded
         */
        public Builder appendQueryParameter(@NonNull String key, @NonNull Number value) {
            mBuilder.appendQueryParameter(key, value.toString());
            return this;
        }

        /**
         * Encodes the key and value and then appends the parameter to the
         * query string.
         *
         * @param key   which will be encoded
         * @param value which will be encoded
         */
        public Builder appendQueryParameter(@NonNull String key, @NonNull Boolean value) {
            mBuilder.appendQueryParameter(key, value.toString());
            return this;
        }

        /**
         * Clears the the previously set query.
         */
        public Builder clearQuery() {
            mBuilder.clearQuery();
            return this;
        }

        /**
         * open url
         *
         * @param uri uri
         * @return builder
         */
        public Builder withUrl(@NonNull String uri) {
            mBuilder = Uri.parse(uri).buildUpon();
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

        /**
         * Constructs a Uri with the current attributes.
         */
        public Route build() {
            Route route = new Route();
            route.setUri(mBuilder.build());
            route.setFlags(mFlags);
            route.setEnterAnim(mEnterAnim);
            route.setExitAnim(mExitAnim);
            return route;
        }

        @Override
        public String toString() {
            return build().toString();
        }
    }

}
