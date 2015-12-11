package com.github.lwz0316.httpagent.impl;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;

import com.github.lwz0316.httpagent.Header;
import com.github.lwz0316.httpagent.Response;

/**
 * Created by liuwenzhu on 2015/12/11.
 */
public class SafeResponseWrapper implements Response {

    private Response mResponse;
    private Handler mHandler;

    public SafeResponseWrapper(Response response) {
        this(null, response);
    }
    public SafeResponseWrapper(@Nullable Looper looper, Response response) {
        if (looper != null) {
            mHandler = new Handler(looper);
        }
        mResponse = response;
    }


    @Override
    public void onStart() {
        if (mResponse != null) {
            if (mHandler != null) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mResponse.onStart();
                    }
                });
            } else {
                mResponse.onStart();
            }
        }
    }

    @Override
    public void onSuccess(final int statusCode, final Header[] headers, final byte[] data) {
        if (mResponse != null) {
            if (mHandler != null) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mResponse.onSuccess(statusCode, headers, data);
                    }
                });
            } else {
                mResponse.onSuccess(statusCode, headers, data);
            }
        }
    }

    @Override
    public void onError(final Throwable t) {
        if (mResponse != null) {
            if (mHandler != null) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mResponse.onError(t);
                    }
                });
            } else {
                mResponse.onError(t);
            }
        }
    }

    @Override
    public void onComplete() {
        if (mResponse != null) {
            if (mHandler != null) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mResponse.onComplete();
                    }
                });
            } else {
                mResponse.onComplete();
            }
        }
    }
}
