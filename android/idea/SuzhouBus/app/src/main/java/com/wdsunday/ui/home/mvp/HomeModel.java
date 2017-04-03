package com.wdsunday.ui.home.mvp;

import android.content.Context;

import com.framwork.mvpbase.model.MvpModel;
import com.framwork.mvpbase.model.impl.MvpBaseModel;
import com.wdsunday.http.HttpManger;
import com.wdsunday.http.SendData;

/**
 * Created by liangjun on 2017/2/3.
 */

public class HomeModel extends MvpBaseModel {
    public void getTotal(String lineNmum, SendData sendData, Context context){
        HttpManger.useOkHttp(lineNmum,sendData,context);

    }


}
