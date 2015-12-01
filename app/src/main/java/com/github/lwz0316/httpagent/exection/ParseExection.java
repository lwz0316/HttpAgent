package com.github.lwz0316.httpagent.exection;

/**
 * 解析失败异常
 */
public class ParseExection extends Exception {

    public ParseExection(Throwable throwable) {
        super(throwable);
    }

    public ParseExection(String detailMessage) {
        super(detailMessage);
    }
}
