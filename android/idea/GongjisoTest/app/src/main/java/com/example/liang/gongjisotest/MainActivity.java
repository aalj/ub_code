package com.example.liang.gongjisotest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import okhttp3.*;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button but = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        but = (Button) findViewById(R.id.but);
        but.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
//        useOkHttp();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                useOkHttp();
            }
        });
        thread.start();
    }


    public void useOkHttp() {
        // 表单提交 这种能满足大部分的需求
        RequestBody formBody = new FormBody.Builder()
                .add("__VIEWSTATE", "/wEPDwUJNDk3MjU2MjgyD2QWAmYPZBYCAgMPZBYCAgEPZBYCAgYPDxYCHgdWaXNpYmxlaGRkZArYd9NZeb6lYhNOScqHVvOmnKWkIejcJ7J2157Nz6l1")
                .add("__VIEWSTATEGENERATOR", "964EC381")
                .add("__EVENTVALIDATION", "/wEWAwL5m9CTDgL88Oh8AqX89aoKFjHWxIvicIW2NoJRKPFu7zDvdWiw74UWlUePz1dAXk4=")
                .add("ctl00$MainContent$LineName", "228")
                .add("ctl00$MainContent$SearchLine", "搜索")
                .build();
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://www.szjt.gov.cn/apts/APTSLine.aspx")
                .post(formBody)
                .build();
        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                String json = response.body().string();
                System.out.println(json);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }






}
