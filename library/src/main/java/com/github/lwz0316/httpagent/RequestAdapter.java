package com.github.lwz0316.httpagent;

import java.util.Map;

/**
 * 请求接口适配器
 * <p>将不同的Http请求类，通过适配器来转换为统一的接口，供 {@link HttpAgent} 调用</p>
 */
public interface RequestAdapter<T> {

    /**
     * @param tag     请求的 标记，主要用来 {@link #cancelRequest()}
     * @param async  是否为异步请求。 true 异步请求(async)，否则为同步请求(sync)
     * @param method {@link HttpMethod}
     * @param url
     * @param headers
     * @param params
     * @param response
     */
    void request(Object tag, boolean async, HttpMethod method, String url, Header[] headers, Map<String, ?> params, Response response);

    /**
     * 取消请求
     */
    void cancelRequest();

    T getHttpClient();

}
