package com.github.lwz0316.httpagent;

import java.util.Map;

/**
 * Http 请求响应接口
 *
 * @param <T> 返回的实体
 */
public interface Response<T> {
    void onResponse(int statusCode, Map<String, String>[] headers, T data );
}
