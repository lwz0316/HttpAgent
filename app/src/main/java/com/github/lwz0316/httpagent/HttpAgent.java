package com.github.lwz0316.httpagent;

import java.util.Map;

/**
 * Created by liuwenzhu on 2015/11/30.
 */
public class HttpAgent {

    private int mConnectTimeout = 3000;
    private int mSocketTimeout = 3000;
    private int mMaxConnectedCount = 5;
    private HttpClientAdapter mHttpClientAdapter;

    private HttpAgent(Builder builder) {
        mHttpClientAdapter = builder.httpClientAdapter;
        mConnectTimeout = builder.connectTimeout;
        mSocketTimeout = builder.socketTimeout;
        mMaxConnectedCount = builder.maxConnectedCount;
    }

    public static void get(String url, Map<String, ?> params, Response<?> response) {

    }

    public static void getSync(String url, Map<String, ?> params, Response<?> response) {
        
    }

    public static class Builder {

        private int connectTimeout = 3000;
        private int socketTimeout = 3000;
        private int maxConnectedCount = 5;
        private HttpClientAdapter httpClientAdapter;

        public Builder(HttpClientAdapter clientAdapter) {
            this.httpClientAdapter = httpClientAdapter;
        }

        public Builder setConnectTimeout(int connectTimeout) {
            this.connectTimeout = connectTimeout;
            return this;
        }

        public Builder setSocketTimeout(int socketTimeout) {
            this.socketTimeout = socketTimeout;
            return this;
        }

        public Builder setMaxConnectedCount(int maxConnectedCount) {
            this.maxConnectedCount = maxConnectedCount;
            return this;
        }

        public HttpAgent build() {
            return new HttpAgent(this);
        }
    }
}
