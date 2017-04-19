package com.github.lwz0316.httpagent.impl;

import com.github.lwz0316.httpagent.Header;
import com.github.lwz0316.httpagent.Response;

/**
 * Created by liuwenzhu on 2015/12/14.
 */
public abstract class TextResponse implements Response {

    @Override
    public void onSuccess(int statusCode, Header[] headers, byte[] data) {
        onSuccess(statusCode,headers, new String(data));
    }

    public abstract void onSuccess(int statusCode, Header[] headers, String data);
}
