package com.github.lwz0316.httpagent.sample.http.adapter;

import android.os.Looper;
import android.util.Log;

import com.github.lwz0316.httpagent.Header;
import com.github.lwz0316.httpagent.HttpMethod;
import com.github.lwz0316.httpagent.HttpUrlUtils;
import com.github.lwz0316.httpagent.RequestAdapter;
import com.github.lwz0316.httpagent.impl.SafeResponseWrapper;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.Map;

/**
 * Created by liuwenzhu on 2015/12/11.
 */
public abstract class BaseOkHttpAdapter implements RequestAdapter<OkHttpClient> {

    private static OkHttpClient sOKOkHttpClient;
    private Object mRequestTag;

    @Override
    public void request(Object tag, boolean async, HttpMethod method, String url, Header[] headers, Map<String, ?> params, com.github.lwz0316.httpagent.Response response) {
        mRequestTag = tag;

        String originUrl;
        if (HttpMethod.GET == method) {
            originUrl = HttpUrlUtils.buildHttpGetParams(url, params);
        } else {
            originUrl = url;
        }
        Log.d("#### url", originUrl);

        Request.Builder builder = addHeadersToBuilder(new Request.Builder(), headers)
                .url(originUrl)
                .tag(tag);

        if (HttpMethod.GET == method) {
            builder.get();
        } else {
            builder.method(method.toString(), createRequestBody(params));
        }

        if (async) {
            requestAsync(builder.build(), response);
        } else {
            requestSync(builder.build(), response);
        }
    }

    protected abstract RequestBody createRequestBody(Map<String, ?> params);

    protected void requestAsync(final Request request, final com.github.lwz0316.httpagent.Response response) {
        final SafeResponseWrapper mResponse = new SafeResponseWrapper(Looper.myLooper(), response);
        mResponse.onStart();
        getHttpClient().newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Request request, IOException e) {
                mResponse.onError(e);
                mResponse.onComplete();
            }

            @Override
            public void onResponse(com.squareup.okhttp.Response response) throws IOException {
                mResponse.onSuccess(response.code(), convertHeader(response.headers()), response.body().bytes());
                mResponse.onComplete();
            }
        });
    }

    protected void requestSync(Request request, com.github.lwz0316.httpagent.Response responseCallback) {
        final SafeResponseWrapper safeResponse = new SafeResponseWrapper(responseCallback);
        safeResponse.onStart();
        try {
            Response response = getHttpClient().newCall(request).execute();
            safeResponse.onSuccess(response.code(), convertHeader(response.headers()), response.body().bytes());
        } catch (IOException e) {
            safeResponse.onError(e);
        } finally {
            safeResponse.onComplete();
        }
    }


    @Override
    public void cancelRequest() {
        if (mRequestTag != null) {
            getHttpClient().cancel(mRequestTag);
        }
    }

    @Override
    public OkHttpClient getHttpClient() {
        if (sOKOkHttpClient == null) {
            sOKOkHttpClient = new OkHttpClient();
        }
        return sOKOkHttpClient;
    }

    private Request.Builder addHeadersToBuilder(Request.Builder builder, Header[] headers) {
        if (headers != null) {
            final int headerSize = headers.length;
            String[] keyValueArray = new String[headers.length * 2];
            Header header;
            for (int i = 0, index; i < headerSize; i++) {
                header = headers[i];
                index = i * 2;
                keyValueArray[index] = header.key;
                keyValueArray[index + 1] = header.value;
            }
            builder.headers(Headers.of(keyValueArray));
        }
        return builder;
    }

    private Header[] convertHeader(Headers headers) {
        Header[] headerArr = new Header[headers.size()];
        String key;
        for (int i = 0, size = headerArr.length; i < size; i++) {
            headerArr[i] = new Header(key = headers.name(i), headers.get(key));
        }
        return headerArr;
    }

}
