package com.github.lwz0316.httpagent;

/**
 * Created by liuwenzhu on 2015/12/10.
 */
public class Header {

    public String key;
    public String value;

    public Header(String key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Header{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
