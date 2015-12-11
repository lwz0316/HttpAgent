package com.github.lwz0316.httpagent;

/**
 * Created by liuwenzhu on 2015/12/10.
 */
public class Config {

    private static final int DEFAULT_SOCKET_TIMEOUT = 15 * 1000;	// Socket 超时时间
    private static final int DEFAULT_CONNECT_TIMEOUT = 15 * 1000;	// 连接超时时间
    private static final int DEFAULT_MAX_CONNECTIONS = 5;		    // 最大连接数
    private static final int DEFAULT_RETRY_TIMES = 1;			    // 重试次数

    private int connectTimeout = DEFAULT_CONNECT_TIMEOUT;
    private int socketTimeout = DEFAULT_SOCKET_TIMEOUT;
    private int maxConnections = DEFAULT_MAX_CONNECTIONS;
    private int retryTimes = DEFAULT_RETRY_TIMES;
    private RequestAdapter requestAdapter;

    public Config(RequestAdapter requestAdapter) {
        this.requestAdapter = requestAdapter;
    }

    public Config setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
        return this;
    }

    public Config setSocketTimeout(int socketTimeout) {
        this.socketTimeout = socketTimeout;
        return this;
    }

    public Config setMaxConnections(int maxConnections) {
        this.maxConnections = maxConnections;
        return this;
    }

    public Config setRetryTimes(int retryTimes) {
        this.retryTimes = retryTimes;
        return this;
    }
}
