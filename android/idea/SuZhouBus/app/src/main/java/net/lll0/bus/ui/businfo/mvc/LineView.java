package net.lll0.bus.ui.businfo.mvc;


import net.lll0.bus.ui.businfo.entity.RealTImeInfoEntity;
import net.lll0.framwork.mvpbase.view.MvpView;
import net.lll0.bus.database.bean.LineInfoBean;

import java.util.List;

/**
 * Created by liangjun on 2017/4/3.
 */

public interface LineView extends MvpView {

    void getLineInfo(List<LineInfoBean> lineInfoBeen);
    void getLineRealTimeInfo(RealTImeInfoEntity realTImeInfoEntity);




}
