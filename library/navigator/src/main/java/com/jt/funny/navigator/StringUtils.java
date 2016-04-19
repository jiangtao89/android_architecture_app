package com.jt.funny.navigator;

/**
 * Created by jiangtao on 16/3/27.
 *
 * @author jiangtao
 * @version 1.0.0
 */
public class StringUtils {

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
}
