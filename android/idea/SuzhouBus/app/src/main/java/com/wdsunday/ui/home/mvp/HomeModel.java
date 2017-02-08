package com.wdsunday.ui.home.mvp;

import com.framwork.mvpbase.model.MvpModel;
import com.framwork.mvpbase.model.impl.MvpBaseModel;
import com.wdsunday.http.HttpManger;
import com.wdsunday.http.SendData;

/**
 * Created by liangjun on 2017/2/3.
 */

public class HomeModel extends MvpBaseModel {
    public void getTotal(String lineNmum,SendData sendData){
        HttpManger.useOkHttp(lineNmum,sendData);

    }

    public void getLineInfo(String param , SendData sendData){
        HttpManger.useOkHttpGet(param,sendData);

    }
}
