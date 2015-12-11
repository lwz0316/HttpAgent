package com.github.lwz0316.httpagent;

import java.util.Map;
import java.util.Set;

/**
 * Created by liuwenzhu on 2015/12/11.
 */
public final class HttpUrlUtils {

    private HttpUrlUtils() {
    }

    public static String buildHttpGetParams(String url, Map<String, ?> params){
        return formatBaseUrl(url) + formatParams(params);
    }

    public static String buildHttpGetParam(String url, String name, String value){
        return formatBaseUrl(url) + name + "=" + value;
    }

    private static String formatBaseUrl(String url) {
        if (url.contains("?")) {
            return url + "&";
        }
        return url + "?";
    }

    private static String formatParams(Map<String, ?> params) {
        StringBuilder sb = new StringBuilder();
        Set<? extends Map.Entry<String, ?>> entrySet = params.entrySet();
        for (Map.Entry<String, ?> entry : entrySet) {
            sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }
}
