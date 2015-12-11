package com.github.lwz0316.httpagent;

/**
 * Http 响应处理接口
 *
 * @param <E> 服务器返回的数据类型
 * @param <T> 期望的解析数据类型
 */
public interface ResponseHandler<E, T>  extends Response {

    /**
     * 解析成功
     * <p> 当 status = SUCCESS 时，调用此方法
     * @param result
     * @param responseBody 服务器返回的原始结果
     */
    public void onParseSuccess(T result, E responseBody);

    /**
     * 解析异常
     * @param t
     */
    public void onParseError(Throwable t);

    /**
     * 获取解析器
     * @return
     */
    public Parser<E, T> getParser();
}
