package com.wdsunday.http;

import android.support.annotation.NonNull;

import com.wdsunday.contstant.BaseConsTent;
import okhttp3.*;

import java.io.IOException;

/**
 * Created by liang on 2017/1/1.
 */
public class HttpManger {


    public static void useOkHttp( String lineNum, final SendData sendData) {

        // 表单提交 这种能满足大部分的需求
        RequestBody formBody = new FormBody.Builder()
                .add("__VIEWSTATE", "/wEPDwUJNDk3MjU2MjgyD2QWAmYPZBYCAgMPZBYCAgEPZBYCAgYPDxYCHgdWaXNpYmxlaGRkZArYd9NZeb6lYhNOScqHVvOmnKWkIejcJ7J2157Nz6l1")
                .add("__VIEWSTATEGENERATOR", "964EC381")
                .add("__EVENTVALIDATION", "/wEWAwL5m9CTDgL88Oh8AqX89aoKFjHWxIvicIW2NoJRKPFu7zDvdWiw74UWlUePz1dAXk4=")
                .add("ctl00$MainContent$LineName", lineNum)
                .add("ctl00$MainContent$SearchLine", "搜索")
                .build();

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(BaseConsTent.HTTP_URL)
                .post(formBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()) {
                    sendData.sendString(response.body().string());
                }
            }
        });



    }



    public static void useOkHttpGet( String param,final SendData sendData) {



        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(BaseConsTent.HTTP_URL+"?"+param)
                .get()
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()) {
                    sendData.sendString(response.body().string());
                }
            }
        });



    }


}
