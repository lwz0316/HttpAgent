package com.github.lwz0316.httpagent.impl;

import android.os.Handler;
import android.os.Looper;

import com.github.lwz0316.httpagent.Header;
import com.github.lwz0316.httpagent.Parser;
import com.github.lwz0316.httpagent.ResponseHandler;
import com.github.lwz0316.httpagent.TypeUtil;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Http 响应处理类
 *
 * @param <T> 期望的解析数据类型
 */
public abstract class ResponseParserHandler<T> implements ResponseHandler<byte[], T> {

    private static final int CUP_COUNT = Runtime.getRuntime().availableProcessors();
    private static final int CORE_POLL_SIZE = CUP_COUNT + 1;
    private static Executor sParsePoolExecutor = Executors.newFixedThreadPool(CORE_POLL_SIZE);

    private Poster mResultPoster;

    public ResponseParserHandler() {
        this(Looper.myLooper());
    }

    public ResponseParserHandler(Looper resultLooper) {
        if (resultLooper != null) {
            mResultPoster = new HandlerPoster(new Handler(resultLooper));
        } else {
            mResultPoster = new DirectPoster();
        }
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, final byte[] data) {
        if (mResultPoster instanceof DirectPoster) {
            parse(data);
        } else {
            sParsePoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    parse(data);
                }
            });
        }
    }

    private void parse(final byte[] data) {
        try {
            Parser<byte[], T> parser = getParser();
            final T result = parser.parse(data, TypeUtil.getGenericType(this.getClass()));
            postSuccess(result, data);
        } catch (Exception parseException) {
            postError(parseException, data);
        } finally {
            mResultPoster.free();
        }
    }

    private void postSuccess(final T result, final byte[] data) {
        mResultPoster.post(new Runnable() {
            @Override
            public void run() {
                onParseSuccess(result, data);
            }
        });
    }

    private void postError(final Throwable t, final byte[] data) {
        mResultPoster.post(new Runnable() {
            @Override
            public void run() {
                onParseError(t, data);
            }
        });
    }

    private static interface Poster {

        void post(Runnable r);

        void free();
    }

    private static class DirectPoster implements Poster {
        @Override
        public void post(Runnable r) {
            r.run();
        }

        public void free() {

        }
    }

    private static class HandlerPoster implements Poster {

        private Handler handler;

        public HandlerPoster(Handler handler) {
            this.handler = handler;
        }

        @Override
        public void post(Runnable r) {
            handler.post(r);
        }

        public void free() {
            handler = null;
        }
    }
}
