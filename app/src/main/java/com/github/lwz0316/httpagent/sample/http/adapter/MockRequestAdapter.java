package com.github.lwz0316.httpagent.sample.http.adapter;

import android.content.Context;
import android.util.Log;

import com.github.lwz0316.httpagent.Header;
import com.github.lwz0316.httpagent.HttpMethod;
import com.github.lwz0316.httpagent.RequestAdapter;
import com.github.lwz0316.httpagent.Response;
import com.google.gson.Gson;

import java.util.Map;

/**
 * Created by liuwenzhu on 2015/12/10.
 */
public class MockRequestAdapter implements RequestAdapter<StringBuffer> {

    Object tag;
    StringBuffer sb = new StringBuffer();

    @Override
    public void request(Context context, Object tag, boolean async, HttpMethod method, String url, Header[] headers, Map<String, ?> params, Response response) {
        this.tag = tag;
        Gson gson = new Gson();
        sb.append(method.toString())
                .append("\n").append(url)
                .append("\n").append(headers == null ? "No Header" : gson.toJson(headers))
                .append("\n").append(params == null ? "No Params" : gson.toJson(params));
        if (response != null) {
            response.onStart();
            response.onSuccess(200, headers, sb.toString().getBytes());
            response.onComplete();
        }
        sb.setLength(0);
    }

    @Override
    public void cancelRequest() {
        sb.setLength(0);
        sb.append("tag: ").append(tag);
        Log.d("####", sb.toString());
    }

    @Override
    public StringBuffer getHttpClient() {
        return sb;
    }
}
