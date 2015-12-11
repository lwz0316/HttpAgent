package com.github.lwz0316.httpagent.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.github.lwz0316.httpagent.Header;
import com.github.lwz0316.httpagent.HttpAgent;
import com.github.lwz0316.httpagent.Response;
import com.github.lwz0316.httpagent.sample.http.adapter.FormOkHttpAdapter;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    TextView mConsole;

    HttpAgent mAgent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mConsole = (TextView) findViewById(R.id.console);


        Map<String, Object> params = new HashMap<String, Object>();
        params.put("code", 1);
        params.put("sex", "M");

        mAgent = new HttpAgent.Builder(this, new FormOkHttpAdapter(), "http://www.baidu.com")
                .header("multi", "header1")
                .header(new Header("multi", "header2"))
                .headers(new Header("multi", "header3"), new Header("multi", "header4"))
                .header(new Header("contentType", "application/json"))
                .header(new Header("charset", "utf-8"))
                .param("username", "lwz")
                .param("pwd", "xxxxxx")
                .params(params)
                .response(new Response() {
                    @Override
                    public void onStart() {
                        printLogToConsole("onStart ----");
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] data) {
                        printLogToConsole("onSuccess ----");
                        printLogToConsole(statusCode);
                        printLogToConsole(formatHeader(headers));
                        printLogToConsole("RESPONSE DATA ----");
                        printLogToConsole(new String(data));
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
                })
                .tag("TARGET").build();

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
        mAgent.get();
    }
    public void doPost(MenuItem item) {
        mAgent.post();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAgent.getRequestAdapter().cancelRequest();
    }

    private String formatHeader(Header[] headers) {
        StringBuilder sb = new StringBuilder("HEADERS ----\n");
        for (Header header : headers) {
            sb.append(header.key).append(" : ").append(header.value);
        }
        return sb.toString();
    }
}
