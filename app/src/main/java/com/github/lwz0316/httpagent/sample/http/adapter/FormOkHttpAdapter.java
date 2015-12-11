package com.github.lwz0316.httpagent.sample.http.adapter;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.RequestBody;

import java.util.Map;
import java.util.Set;

/**
 * Created by liuwenzhu on 2015/12/11.
 */
public class FormOkHttpAdapter extends BaseOkHttpAdapter {

    @Override
    protected RequestBody createRequestBody(Map<String, ?> params) {
        return addParamsToFormBuilder(new FormEncodingBuilder(), params).build();
    }

    private FormEncodingBuilder addParamsToFormBuilder(FormEncodingBuilder builder, Map<String, ?> params) {
        if (params != null) {
            Set<? extends Map.Entry<String, ?>> entrySet = params.entrySet();
            for ( Map.Entry<String, ?> entry : entrySet) {
                builder.add(entry.getKey(), String.valueOf(entry.getValue()));
            }
        }
        return builder;
    }

}
