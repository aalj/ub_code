package net.lll0.bus.ui.stationinfo.mvp;

import android.app.Activity;
import android.content.Context;

import net.lll0.bus.database.ParsesHomeData;
import net.lll0.bus.database.bean.LineInfoBean;
import net.lll0.bus.database.bean.StationInfoBean;
import net.lll0.bus.http.SendData;
import net.lll0.framwork.mvpbase.presenter.impl.MvpBasePresenter;

import java.util.List;
import java.util.TimerTask;

/**
 * Created by stone on 17-7-29.
 */

public class StationInfoPresenter extends MvpBasePresenter<StationInfoView> {
    StationInfoModel stationInfoModel;
    Context context;


    public StationInfoPresenter(Context context) {
        super(context);
        this.stationInfoModel = new StationInfoModel();
        this.context = context;
    }


    public void getStationInfo(String param) {

        //都是get请求 所以共用同样的网络请求方法
        stationInfoModel.getStaticInfo(param, new SendData() {
            @Override
            public void success(String data) {
                ParsesHomeData parsesHomeData = new ParsesHomeData(data);
                final List<StationInfoBean> stationInfoBeen = parsesHomeData.parseHtmlStationToLineInfo();


                ((Activity) context).runOnUiThread(new TimerTask() {
                    @Override
                    public void run() {
                        getView().getStaticInfo(stationInfoBeen);
                    }
                });
            }

            @Override
            public void fail(Exception e) {
                ((Activity) context).runOnUiThread(new TimerTask() {
                    @Override
                    public void run() {
                        error();
                    }
                });
            }
        }, context);

    }
}
