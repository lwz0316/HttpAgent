package com.github.lwz0316.httpagent.sample.http.adapter;

import com.google.gson.Gson;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.RequestBody;

import java.util.Map;

/**
 * Created by liuwenzhu on 2017/4/19.
 */
public class JSONHttpAdapter extends BaseOkHttpAdapter {
    @Override
    protected RequestBody createRequestBody(Map<String, ?> params) {
        return RequestBody.create(MediaType.parse("application/json"), new Gson().toJson(params));
    }
}
