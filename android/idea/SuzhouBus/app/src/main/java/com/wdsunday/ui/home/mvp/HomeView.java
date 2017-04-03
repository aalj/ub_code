package com.wdsunday.ui.home.mvp;

import com.framwork.mvpbase.view.MvpView;
import com.wdsunday.database.bean.LineInfoBean;
import com.wdsunday.database.bean.SearchLineBean;

import java.util.List;

/**
 * Created by liangjun on 2017/2/3.
 */

public interface HomeView extends MvpView {

    void getTotalLines(List<SearchLineBean> lineBeens);



}
