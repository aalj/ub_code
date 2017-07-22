package net.lll0.bus.ui.businfo.mvc;

import android.app.Activity;
import android.content.Context;


import net.lll0.framwork.mvpbase.presenter.impl.MvpBasePresenter;
import net.lll0.bus.database.ParsesHomeData;
import net.lll0.bus.database.bean.LineInfoBean;
import net.lll0.bus.http.SendData;

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
