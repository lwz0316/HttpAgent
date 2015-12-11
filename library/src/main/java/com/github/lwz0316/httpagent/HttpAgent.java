package com.github.lwz0316.httpagent;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liuwenzhu on 2015/11/30.
 */
public class HttpAgent {

    private Context mContext;
    private HttpClientAdapter mHttpClientAdapter;
    private String mUrl;
    private Header[] mHeaders;
    private Map<String, ?> mParams;
    private Response mResponse;
    private Object mTarget;

    private HttpAgent(Builder builder) {
        mContext = builder.context;
        mHttpClientAdapter = builder.httpClientAdapter;
        mUrl = builder.url;
        mParams = builder.params;
        mResponse = builder.response;
        mTarget = builder.tag;

        final List<Header> headers = builder.headers;
        mHeaders = ( headers == null
                ? null
                : headers.toArray(new Header[headers.size()]) );
    }

    public HttpClientAdapter getDefaultRequestAdapter() {
        // TODO
        return null;
    }

    public void get() {
       execute(HttpMethod.GET);
    }

    public void post() {
        execute(HttpMethod.POST);
    }

    public void execute(HttpMethod method) {
        mHttpClientAdapter.request(mContext, mTarget, true, method, mUrl, mHeaders, mParams, mResponse);
    }

    public HttpClientAdapter getRequestAdapter() {
        return mHttpClientAdapter;
    }

    public static final class Builder {

        private Context context;
        private HttpClientAdapter httpClientAdapter;
        private String url;
        private ArrayList<Header> headers;
        private Map<String, Object> params;
        private Response response;
        private Object tag;

        public Builder(@NonNull Context context, @NonNull HttpClientAdapter httpClientAdapter, @NonNull String url) {
            this.context = context;
            this.httpClientAdapter = httpClientAdapter;
            this.url = url;
        }

        public Builder header(String key, String value) {
            header(new Header(key, value));
            return this;
        }

        public Builder header(@NonNull Header header) {
            if (headers == null) {
                headers = new ArrayList<Header>(1);
            }
            headers.add(header);
            return this;
        }

        public Builder headers(@NonNull Header... headers) {
            for (Header header : headers) {
                header(header);
            }
            return this;
        }

        public Builder param(String key, Object value) {
            if (this.params == null) {
                this.params = new HashMap<String, Object>(1);
            }
            this.params.put(key, value);
            return this;
        }

        public Builder params(Map<String, Object> params) {
            if (this.params == null) {
                this.params = new HashMap<String, Object>(1);
            }
            this.params.putAll(params);
            return this;
        }

        public Builder response(Response response) {
            this.response = response;
            return this;
        }

        public Builder tag(Object tag) {
            this.tag = tag;
            return this;
        }

        public HttpAgent build() {
            return new HttpAgent(this);
        }
    }
}
