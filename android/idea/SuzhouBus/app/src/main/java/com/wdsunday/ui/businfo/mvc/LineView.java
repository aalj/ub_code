package com.wdsunday.ui.businfo.mvc;

import com.framwork.mvpbase.view.MvpView;
import com.wdsunday.database.bean.LineInfoBean;

import java.util.List;

/**
 * Created by liangjun on 2017/4/3.
 */

public interface LineView extends MvpView {

    void getLineInfo(List<LineInfoBean> lineInfoBeen);

}
