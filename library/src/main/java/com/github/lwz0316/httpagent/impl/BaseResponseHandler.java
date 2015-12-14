package com.github.lwz0316.httpagent.impl;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

import com.github.lwz0316.httpagent.Header;
import com.github.lwz0316.httpagent.Parser;
import com.github.lwz0316.httpagent.ResponseHandler;
import com.github.lwz0316.httpagent.TypeUtil;
import com.github.lwz0316.httpagent.exection.ParseExection;

/**
 * Http 响应基础处理类
 *
 * @param <T> 期望的解析数据类型
 */
public abstract class BaseResponseHandler<T> implements ResponseHandler<byte[], T> {

    HandlerThread mParseThread;
    Handler mParseHandler;
    Handler mResultHandler;

    public BaseResponseHandler() {
        this(Looper.myLooper());
    }

    public BaseResponseHandler(Looper resultLooper) {
        mParseThread = new HandlerThread(getClass().getName());
        mParseThread.start();
        mParseHandler = new Handler(mParseThread.getLooper());
        mResultHandler = new Handler(resultLooper);
    }


    @Override
    public void onSuccess(int statusCode, Header[] headers, final byte[] data) {
        mParseHandler.post(new Runnable() {
            @Override
            public void run() {
                parse(data);
            }

        });
    }

    private void parse(final byte[] data) {
        try {
            Parser<byte[], T> parser = getParser();
            final T result = parser.parse(data, TypeUtil.getGenericType(this.getClass()));
            mResultHandler.post(new Runnable() {
                @Override
                public void run() {
                    onParseSuccess(result, data);
                }
            });
        } catch (final ParseExection parseExection) {
            mResultHandler.post(new Runnable() {
                @Override
                public void run() {
                    onParseError(parseExection);
                }
            });
        } finally {
            if (mParseThread != null) {
                mParseThread.quit();
                mParseThread = null;
            }
        }
    }
}
