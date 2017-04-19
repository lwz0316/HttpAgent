package com.github.lwz0316.httpagent.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.github.lwz0316.httpagent.Header;
import com.github.lwz0316.httpagent.HttpAgent;
import com.github.lwz0316.httpagent.Parser;
import com.github.lwz0316.httpagent.exection.ParseException;
import com.github.lwz0316.httpagent.impl.ResponseParserHandler;
import com.github.lwz0316.httpagent.sample.http.adapter.MockJsonRequestAdapter;
import com.google.gson.Gson;

import java.lang.reflect.Type;

public class MainActivity extends AppCompatActivity {

    TextView mConsole;

    HttpAgent mAgent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mConsole = (TextView) findViewById(R.id.console);

        HttpAgent.setDefaultRequestAdapter(new MockJsonRequestAdapter());
    }

    private HttpAgent createHttpAgent() {
        return mAgent = new HttpAgent.Builder("https://yesno.wtf/api")
                .tag("TARGET")
                .header("multi", "header1")
                .header(new Header("multi", "header2"))
                .headers(new Header("multi", "header3"), new Header("multi", "header4"))
                .header(new Header("contentType", "application/json"))
                .header(new Header("charset", "utf-8"))
                .param("username", "lwz")
                .param("pwd", "xxxxxx")
                .response(new ResponseParserHandler<People>() {

                    @Override
                    public void onStart() {
                        printLogToConsole("onStart ----");
                    }

                    @Override
                    public void onError(Throwable t) {
                        printLogToConsole("onError ----");
                        printLogToConsole(t);
                    }

                    @Override
                    public void onComplete() {
                        printLogToConsole("onComplete ----");
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] data) {
                        printLogToConsole(formatHeader(headers));
                        super.onSuccess(statusCode, headers, data);
                    }

                    @Override
                    public void onParseSuccess(People result, byte[] responseBody) {
                        printLogToConsole("onParseSuccess ---- ");
                        printLogToConsole(new Gson().toJson(result));
                        printLogToConsole(new String(responseBody));
                    }

                    @Override
                    public void onParseError(Throwable t, byte[] responseBody) {
                        printLogToConsole("onParseError ----");
                        printLogToConsole(new String(responseBody));
                        printLogToConsole(t);
                    }

                    @Override
                    public Parser<byte[], People> getParser() {
                        return new Parser<byte[], People>() {
                            @Override
                            public People parse(byte[] data, Type typeOfT) throws ParseException {
                                return new Gson().fromJson(new String(data), typeOfT);
                            }
                        };
                    }
                })
                .build();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    void printLogToConsole(Object log) {
        mConsole.append("\n");
        mConsole.append(String.valueOf(log));
    }

    public void doCleanConsole(MenuItem item) {
        mConsole.setText(null);
    }

    public void doGet(MenuItem item) {
        if (mAgent != null) {
            mAgent.getRequestAdapter().cancelRequest();
        }
        createHttpAgent().get();
    }
    public void doPost(MenuItem item) {
        if (mAgent != null) {
            mAgent.getRequestAdapter().cancelRequest();
        }
        createHttpAgent().post();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAgent.getRequestAdapter().cancelRequest();
    }

    private String formatHeader(Header[] headers) {
        StringBuilder sb = new StringBuilder("HEADERS ----\n");
        if (headers != null) {
            for (Header header : headers) {
                sb.append(header.key).append(" : ").append(header.value).append("\n");
            }
        }
        return sb.toString();
    }
}
