package com.github.lwz0316.httpagent.impl;

import com.github.lwz0316.httpagent.Parser;

/**
 * Created by liuwenzhu on 2015/12/14.
 */
public abstract class JsonResponseHandler<T> extends BaseResponseHandler<T> {

    @Override
    public Parser<byte[], T> getParser() {
        return new JsonParser<T>();
    }
}
