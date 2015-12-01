package com.github.lwz0316.httpagent;

import java.util.Map;

/**
 * Http 请求接口适配器
 * <p>将不同的Http请求类，通过适配器来转换为统一的接口，供 {@link HttpAgent} 调用</p>
 */
public interface HttpClientAdapter {

    /**
     * @param method {@link HttpMethod}
     * @param url
     * @param params
     * @param response
     */
    void request(int method, String url, Map<String, ?> params, Response<?> response);

    /**
     * 取消请求
     * <p>通过请求的 url 来取消对应的请求</p>
     * @param url
     */
    void cancelRequest(String url);

    /**
     * 通过 url 获取标识
     * @param url
     * @return
     */
    String getTarget(String url);
}
