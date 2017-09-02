package net.lll0.bus.ui.stationinfo.mvp;

import android.content.Context;

import net.lll0.bus.http.HttpManger;
import net.lll0.bus.http.SendData;
import net.lll0.framwork.mvpbase.model.impl.MvpBaseModel;

/**
 * Created by stone on 17-7-29.
 */

public class StationInfoModel extends MvpBaseModel {

    public void getStaticInfo(String param , SendData sendData, Context context){
        HttpManger.useOkHttpGet(param,sendData,context);
    }
}
