package com.github.lwz0316.httpagent;

/**
 * Http 响应接口
 */
public interface Response {

    /**
     * 一个请求开始总是会调用这个方法
     */
    void onStart();

    void onSuccess(int statusCode, Header[] headers, byte[] data);

    void onError(Throwable t);

    /**
     * 一个请求最后总是会调用这个方法
     */
    void onComplete();
}
