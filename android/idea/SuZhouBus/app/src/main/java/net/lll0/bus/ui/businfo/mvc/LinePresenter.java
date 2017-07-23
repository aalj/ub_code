package net.lll0.bus.ui.businfo.mvc;

import android.app.Activity;
import android.content.Context;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.lll0.bus.suzhoubus.R;
import net.lll0.bus.ui.businfo.entity.RealTImeInfoEntity;
import net.lll0.framwork.mvpbase.presenter.impl.MvpBasePresenter;
import net.lll0.bus.database.ParsesHomeData;
import net.lll0.bus.database.bean.LineInfoBean;
import net.lll0.bus.http.SendData;

import java.util.List;
import java.util.TimerTask;

import okhttp3.RequestBody;

/**
 * Created by liangjun on 2017/4/3.
 */

public class LinePresenter extends MvpBasePresenter<LineView> {


    private LineModel lineModel;
    private Context context;

    public LinePresenter(Context context) {
        super(context);
        this.context = context;
        lineModel = new LineModel();
    }

    public void getLineInfo(String param) {

        lineModel.getLineInfo(param, new SendData() {
            @Override
            public void success(String data) {
                ParsesHomeData parsesHomeData = new ParsesHomeData(data);
                final List<LineInfoBean> lineInfoBeans = parsesHomeData.parseHtmlLineInfoV2();


                ((Activity) context).runOnUiThread(new TimerTask() {
                    @Override
                    public void run() {
                        getView().getLineInfo(lineInfoBeans);
                    }
                });
            }

            @Override
            public void fail(Exception e) {
                error();
            }
        }, context);

    }

    public void getLineRealTimeInfo(String param, RequestBody formBody) {

        lineModel.getLineRealTImeInfo(param, formBody, new SendData() {
            @Override
            public void success(String data) {
                Gson gson = new GsonBuilder().disableHtmlEscaping().create();
                final RealTImeInfoEntity realTImeInfoEntity = gson.fromJson(data, RealTImeInfoEntity.class);

                ((Activity) context).runOnUiThread(new TimerTask() {
                    @Override
                    public void run() {
                        getView().getLineRealTimeInfo(realTImeInfoEntity);
                    }
                });
            }

            @Override
            public void fail(Exception e) {
                error();
            }
        }, context);

    }

}
