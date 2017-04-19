package com.github.lwz0316.httpagent;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liuwenzhu on 2015/11/30.
 */
public class HttpAgent {

    private RequestAdapter mRequestAdapter;
    private String mUrl;
    private Header[] mHeaders;
    private Map<String, ?> mParams;
    private Response mResponse;
    private Object mTarget;

    private static RequestAdapter sDefaultRequestAdapter;

    private HttpAgent(Builder builder) {
        mUrl = builder.url;
        mParams = builder.params;
        mResponse = builder.response;
        mTarget = builder.tag;

        mRequestAdapter = builder.requestAdapter != null
                ? builder.requestAdapter
                : sDefaultRequestAdapter;

        final List<Header> headers = builder.headers;
        mHeaders = ( headers == null
                ? null
                : headers.toArray(new Header[headers.size()]) );
    }

    public static void setDefaultRequestAdapter(RequestAdapter requestAdapter) {
        sDefaultRequestAdapter = requestAdapter;
    }

    public void get() {
        execute(HttpMethod.GET);
    }

    public void post() {
        execute(HttpMethod.POST);
    }

    public void execute(HttpMethod method) {
        execute(method, true);
    }

    public void execute(HttpMethod method, boolean async) {
        mRequestAdapter.request(mTarget, async, method, mUrl, mHeaders, mParams, mResponse);
    }

    public RequestAdapter getRequestAdapter() {
        return mRequestAdapter;
    }

    public static final class Builder {

        private RequestAdapter requestAdapter;
        private String url;
        private ArrayList<Header> headers;
        private Map<String, Object> params;
        private Response response;
        private Object tag;

        public Builder(@NonNull String url) {
            this.url = url;
        }

        public Builder requestAdapter(RequestAdapter requestAdapter) {
            this.requestAdapter = requestAdapter;
            return this;
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
