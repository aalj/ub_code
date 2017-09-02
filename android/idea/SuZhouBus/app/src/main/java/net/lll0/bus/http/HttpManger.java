package net.lll0.bus.http;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import net.lll0.bus.contstant.BaseConstant;
import net.lll0.bus.exception.NotNetwork;
import net.lll0.bus.ui.other.NotNetworkActivity;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by liang on 2017/1/1.
 */
public class HttpManger {


    public static void useOkHttp(String url, final SendData sendData, Context context) {
        if (!isNetworkAvailable(context)) {
            Intent intent = new Intent(context, NotNetworkActivity.class);
            context.startActivity(intent);
        }


        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(BaseConstant.HTTP_URL + url)
//                .post(formBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                sendData.fail(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    sendData.success(response.body().string());
                }
            }
        });


    }


    public static void useOkHttpGet(String param, final SendData sendData, Context context) {
        if (!isNetworkAvailable(context)) {
            Intent intent = new Intent(context, NotNetworkActivity.class);
            context.startActivity(intent);
        }



        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(BaseConstant.HTTP_URL + param)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                sendData.fail(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String string = response.body().string();
                    sendData.success(string);
                }
            }
        });
    }

    public static void useOkHttpPost(String param,
                                     RequestBody formBody,
                                     final SendData sendData,
                                     Context context) {
        if (!isNetworkAvailable(context)) {
            Intent intent = new Intent(context, NotNetworkActivity.class);
            context.startActivity(intent);
        }



//        // 表单提交 这种能满足大部分的需求
//        RequestBody formBody = new FormBody.Builder()
//                .add("__VIEWSTATE", "/wEPDwUJNDk3MjU2MjgyD2QWAmYPZBYCAgMPZBYCAgEPZBYCAgYPDxYCHgdWaXNpYmxlaGRkZArYd9NZeb6lYhNOScqHVvOmnKWkIejcJ7J2157Nz6l1")
//                .add("__VIEWSTATEGENERATOR", "964EC381")
//                .add("__EVENTVALIDATION", "/wEWAwL5m9CTDgL88Oh8AqX89aoKFjHWxIvicIW2NoJRKPFu7zDvdWiw74UWlUePz1dAXk4=")
////                .add("ctl00$MainContent$LineName", lineNum)
//                .add("ctl00$MainContent$SearchLine", "搜索")
//                .build();
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(BaseConstant.HTTP_URL + param)
                .post(formBody)

                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                sendData.fail(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String string = response.body().string();
                    sendData.success(string);
                }
            }
        });
    }

    //判断当前环境是否能够联网
    private static boolean isNetworkAvailable(final Context context) throws NotNetwork {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkInfo[] allNetworkInfo = cm.getAllNetworkInfo();
            for (NetworkInfo network : allNetworkInfo) {
                if (network.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;

    }


}
