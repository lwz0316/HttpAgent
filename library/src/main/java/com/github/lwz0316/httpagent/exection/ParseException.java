package com.github.lwz0316.httpagent.exection;

/**
 * 解析失败异常
 */
public class ParseException extends Exception {

    public ParseException(Throwable throwable) {
        super(throwable);
    }

    public ParseException(String detailMessage) {
        super(detailMessage);
    }
}
