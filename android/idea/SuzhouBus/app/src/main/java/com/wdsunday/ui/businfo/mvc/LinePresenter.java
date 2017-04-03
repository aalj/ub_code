package com.wdsunday.ui.businfo.mvc;

import android.app.Activity;
import android.content.Context;

import com.framwork.mvpbase.presenter.impl.MvpBasePresenter;
import com.wdsunday.database.ParsesHomeData;
import com.wdsunday.database.bean.LineInfoBean;
import com.wdsunday.http.SendData;
import com.wdsunday.ui.home.mvp.HomeModel;

import java.util.List;
import java.util.TimerTask;

/**
 * Created by liangjun on 2017/4/3.
 */

public class LinePresenter extends MvpBasePresenter<LineView> {


    private LineModel    lineModel;
    private Context context;
    public LinePresenter(Context context) {
        super(context);
       this. context = context;
        lineModel = new LineModel() ;
    }
    public void getLineInfo(String param) {

        lineModel.getLineInfo(param, new SendData() {
            @Override
            public void sendString(String data) {
                ParsesHomeData parsesHomeData = new ParsesHomeData(data);
                final List<LineInfoBean> lineInfoBeans = parsesHomeData.parseHtmlLineInfo();


                ((Activity) context).runOnUiThread(new TimerTask() {
                    @Override
                    public void run() {
                        getView().getLineInfo(lineInfoBeans);
                    }
                });
            }
        },context);

    }

}
