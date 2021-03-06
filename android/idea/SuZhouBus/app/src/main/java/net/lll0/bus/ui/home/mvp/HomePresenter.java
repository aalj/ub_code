package net.lll0.bus.ui.home.mvp;

import android.app.Activity;
import android.content.Context;


import net.lll0.framwork.mvpbase.presenter.impl.MvpBasePresenter;
import net.lll0.bus.database.ParsesHomeData;
import net.lll0.bus.database.bean.SearchLineBean;
import net.lll0.bus.http.SendData;

import java.util.List;
import java.util.TimerTask;

/**
 * Created by liangjun on 2017/2/3.
 */

public class HomePresenter extends MvpBasePresenter<HomeView> {

    private HomeModel totalModel;
    private Context context;

    public HomePresenter(Context context) {
        super(context);
        this.totalModel = new HomeModel();
        this.context = context;
    }

    /**
     * 与model层进行通信
     *
     * @param lineNum
     */
    public void getTotalLines(String lineNum) {
        //这里通过匿名内部类实现网络请求的回调 把数据返回回来
        totalModel.getTotal(lineNum, new SendData() {
            @Override
            public void success(String data) {
                ParsesHomeData parsesHomeData = new ParsesHomeData(data);
                final List<SearchLineBean> lineBeens = parsesHomeData.parseHtmlSearchLineV2();
                getView().getTotalLines(lineBeens);

            }

            @Override
            public void fail(Exception e) {
                error();
            }
        }, context);
    }


}
