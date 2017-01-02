package com.wdsunday.ui.testmvp;

import com.wdsunday.framework.base.model.impl.MvpBaseModel;
import com.wdsunday.http.HttpManger;
import com.wdsunday.http.SendData;

/**
 * Created by stone on 17-1-2.
 */

public class TotalModel extends MvpBaseModel {
    public void getTotal(String lineNmum,SendData sendData){
        HttpManger.useOkHttp(lineNmum,sendData);

    }


}
