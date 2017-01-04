package com.wdsunday.ui.testmvp;

import android.app.Activity;
import android.content.Context;

import com.wdsunday.database.ParsesHomeData;
import com.wdsunday.database.bean.LineInfoBean;
import com.wdsunday.database.bean.SearchLineBean;
import com.wdsunday.framework.base.presenter.impl.MvpBasePresenter;
import com.wdsunday.http.SendData;

import java.util.List;
import java.util.TimerTask;

/**
 * Created by stone on 17-1-2.
 */

public class TotalPresenter extends MvpBasePresenter<TotalView> {

    private TotalModel totalModel;
    private Context context;

    public TotalPresenter(Context context) {
        super(context);
        this.totalModel = new TotalModel();
        this.context = context;
    }


    public void getTotalLines(String lineNum) {
        totalModel.getTotal(lineNum,new SendData() {
            @Override
            public void sendString(String data) {
                ParsesHomeData parsesHomeData = new ParsesHomeData(data);
               final List<SearchLineBean> lineBeens = parsesHomeData.parseHtmlSearchLine();
                ((Activity) context).runOnUiThread(new TimerTask() {
                    @Override
                    public void run() {
                        getView().getTotalLines(lineBeens);

                    }
                });
            }
        });
    }


    public void getLineInfo(String param) {

        totalModel.getLineInfo(param, new SendData() {
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
        });

    }

}
