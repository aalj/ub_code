package com.wdsunday.ui.testmvp;

import com.wdsunday.database.bean.LineInfoBean;
import com.wdsunday.database.bean.SearchLineBean;
import com.wdsunday.framework.base.view.MvpView;

import java.util.List;

/**
 * Created by stone on 17-1-2.
 */

public interface TotalView extends MvpView {

    void getTotalLines(List<SearchLineBean> lineBeens);

    void getLineInfo(List<LineInfoBean> lineInfoBeen);

}
