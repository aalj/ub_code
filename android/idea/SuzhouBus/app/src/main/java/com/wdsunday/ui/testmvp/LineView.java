package com.wdsunday.ui.testmvp;

import com.wdsunday.database.bean.LineInfoBean;
import com.wdsunday.database.bean.SearchLineBean;
import com.wdsunday.framework.base.view.MvpView;

import java.util.List;

/**
 * Created by stone on 17-1-3.
 */

public interface LineView extends MvpView{
    void getLineInfo(List<LineInfoBean> lineInfoBeen);


}
