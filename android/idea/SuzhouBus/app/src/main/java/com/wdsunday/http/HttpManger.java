package com.wdsunday.http;

import android.content.Context;

import com.wdsunday.contstant.BaseConsTent;
import com.wdsunday.utils.wight.ShapeLoadingDialog;

import okhttp3.*;

import java.io.IOException;

/**
 * Created by liang on 2017/1/1.
 */
public class HttpManger {


    static ShapeLoadingDialog shapeLoadingDialog;

    public static void useOkHttp(String lineNum, final SendData sendData, Context context) {
        shapeLoadingDialog = new ShapeLoadingDialog(context);
        shapeLoadingDialog.setCanceledOnTouchOutside(false);
        shapeLoadingDialog.show();
        try {
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
                    shapeLoadingDialog.dismiss();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
                        shapeLoadingDialog.dismiss();
                        sendData.sendString(response.body().string());
                    }
                }
            });
        } catch (Exception e) {
            shapeLoadingDialog.dismiss();
        }


    }


    public static void useOkHttpGet(String param, final SendData sendData, Context context) {
        shapeLoadingDialog = new ShapeLoadingDialog(context);
        shapeLoadingDialog.setCanceledOnTouchOutside(false);
        shapeLoadingDialog.show();
        try {
            String url = "http://www.szjt.gov.cn/apts/?" + param;

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("http://www.szjt.gov.cn/apts/" + param)
//                .url("http://gank.io/api/data/Android/10/1")
                    .build();
//        Request request = new Request.Builder()
//                .url("http://www.szjt.gov.cn/apts/" + param)
//                .get()
//                .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    shapeLoadingDialog.dismiss();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
                        shapeLoadingDialog.dismiss();
                        String string = response.body().string();
                        sendData.sendString(string);
                    }
                }
            });
        } catch (Exception e) {
            shapeLoadingDialog.dismiss();
        }

    }
}
