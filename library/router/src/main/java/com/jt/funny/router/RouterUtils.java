package com.jt.funny.router;

import android.net.Uri;
import android.support.annotation.NonNull;

/**
 * Created by jiangtao on 16/3/27.
 *
 * @author jiangtao
 * @version 1.0.0
 */
class RouterUtils {

    /**
     * isNotEmpty
     *
     * @param value value
     * @return is not empty
     */
    public static boolean isNotEmpty(String value) {
        return value != null && value.length() > 0;
    }

    /**
     * isEmpty
     *
     * @param value value
     * @return is empty
     */
    public static boolean isEmpty(String value) {
        return !isNotEmpty(value);
    }

    public static boolean isEmptySchema(@NonNull String uri) {
        if (RouterUtils.isEmpty(uri)) {
            return true;
        }
        return isEmptySchema(Uri.parse(uri));
    }

    public static boolean isEmptySchema(Uri URI) {
        if (URI == null) {
            return true;
        }
        String schema = URI.getScheme();
        if (RouterUtils.isEmpty(schema)) {
            return true;
        }
        return false;
    }

    /**
     * getURL
     *
     * @param URI URI
     * @return uri
     */
    public static String getURL(Uri URI) {
        if (URI == null) {
            return "";
        }
        String uri = URI.toString();
        int index = uri.indexOf('?');
        if (index > 0) {
            uri = uri.substring(0, index);
        }
        return uri;
    }
}
