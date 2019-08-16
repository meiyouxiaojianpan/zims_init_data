package com.helios.gao.util;

/**
 * @author : gaozhiwen
 * @date : 2019/8/3
 */
public class UrlUtil {

    /**
     * 获取url以系统分隔符分隔的最后一部分
     * @param url
     * @return
     */
    public static String getUrlLastPart(String url) {
        assert url != null;
        int i = url.lastIndexOf("\\") > url.lastIndexOf("/") ? url.lastIndexOf("\\") : url.lastIndexOf("/");
        return url.substring(i + 1);
    }
}
