package com.github.lwz0316.httpagent;

import com.github.lwz0316.httpagent.exection.ParseException;

import java.lang.reflect.Type;

/**
 * 响应结果解析器
 */
public interface Parser<E, T> {

    T parse(E data, Type typeOfT) throws ParseException;

}
