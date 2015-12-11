package com.github.lwz0316.httpagent;

import com.github.lwz0316.httpagent.exection.ParseExection;

/**
 * 响应结果解析器
 */
public interface Parser<E, T> {

    E parse(T data) throws ParseExection;

}
