package com.ekt.cms.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author sxjun
 * @time 2015/8/12 16:28
 */
public class ThreadLocalUtil {
    private static ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<Map<String, Object>>();
    private static final String BASEPATH_KEY = "basepath";

    /**
     *
     * @param basePath
     */
    public static void setBasePath(String basePath) {
        Map<String, Object> map = threadLocal.get();
        if (map == null) {
            map = new HashMap<String, Object>();
            threadLocal.set(map);
        }
        map.put(BASEPATH_KEY, basePath);
    }

    /**
     * @return
     */
    public static String getBasePath() {
        Map<String, Object> map = threadLocal.get();
        System.out.println(map);
        if (map == null) {
            return null;
        }
        Object o = map.get(BASEPATH_KEY);
        return o == null ? null : o.toString();
    }
}
