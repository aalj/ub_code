package net.lll0.bus.ui.home.mvp;

import android.content.Context;

import net.lll0.framwork.mvpbase.model.impl.MvpBaseModel;
import net.lll0.bus.http.HttpManger;
import net.lll0.bus.http.SendData;


/**
 * Created by liangjun on 2017/2/3.
 */

public class HomeModel extends MvpBaseModel {
    public void getTotal(String lineNmum, SendData sendData, Context context){
        HttpManger.useOkHttp(lineNmum,sendData,context);

    }


}
