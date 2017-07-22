package net.lll0.bus.ui.businfo.mvc;

import android.content.Context;

import net.lll0.framwork.mvpbase.model.impl.MvpBaseModel;
import net.lll0.bus.http.HttpManger;
import net.lll0.bus.http.SendData;


/**
 * Created by liangjun on 2017/4/3.
 */

public class LineModel extends MvpBaseModel {

    public void getLineInfo(String param , SendData sendData, Context context){
        HttpManger.useOkHttpGet(param,sendData,context);

    }
}
