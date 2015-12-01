package com.github.lwz0316.httpagent;

/**
 * Created by liuwenzhu on 2015/11/30.
 */
public class HttpAgent {

    private int mConnectTimeout = 3000;
    private int mSocketTimeout = 3000;
    private int mMaxConnectCount = 5;
    private HttpClientAdapter mHttpClientAdapter;

    public void setHttpClientAdapter(HttpClientAdapter httpClientAdapter) {
        mHttpClientAdapter = httpClientAdapter;
    }
}
