package com.github.lwz0316.httpagent.impl;

import com.github.lwz0316.httpagent.Parser;
import com.github.lwz0316.httpagent.exection.ParseExection;
import com.google.gson.Gson;

import java.lang.reflect.Type;

/**
 * Json 解析器
 * @param <T> 解析后返回的类型
 */
public class JsonParser<T> implements Parser<byte[], T> {

    @Override
    public T parse(byte[] data, Type typeOfT) throws ParseExection {
        return new Gson().fromJson(new String(data), typeOfT);
    }
}
