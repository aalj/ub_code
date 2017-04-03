package com.wdsunday.ui.businfo.mvc;

import android.content.Context;

import com.framwork.mvpbase.model.impl.MvpBaseModel;
import com.wdsunday.http.HttpManger;
import com.wdsunday.http.SendData;

/**
 * Created by liangjun on 2017/4/3.
 */

public class LineModel extends MvpBaseModel {

    public void getLineInfo(String param , SendData sendData, Context context){
        HttpManger.useOkHttpGet(param,sendData,context);

    }
}
