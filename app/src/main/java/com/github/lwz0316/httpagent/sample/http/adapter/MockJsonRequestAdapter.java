package com.github.lwz0316.httpagent.sample.http.adapter;

import android.util.Log;

import com.github.lwz0316.httpagent.Header;
import com.github.lwz0316.httpagent.HttpMethod;
import com.github.lwz0316.httpagent.RequestAdapter;
import com.github.lwz0316.httpagent.Response;

import java.util.Map;

/**
 * Created by liuwenzhu on 2015/12/10.
 */
public class MockJsonRequestAdapter implements RequestAdapter<StringBuffer> {

    Object tag;

    @Override
    public void request(Object tag, boolean async, HttpMethod method, String url, Header[] headers, Map<String, ?> params, Response response) {
        this.tag = tag;
        if (response != null) {
            response.onStart();
            final String jsonData = "{\"name\": \"Tom\", \"age\": 20}";
            response.onSuccess(200, headers, jsonData.getBytes());
            response.onComplete();
        }
    }

    @Override
    public void cancelRequest() {
        Log.d("####", "cancelRequest tag: " + tag);
    }

    @Override
    public StringBuffer getHttpClient() {
        return null;
    }
}
