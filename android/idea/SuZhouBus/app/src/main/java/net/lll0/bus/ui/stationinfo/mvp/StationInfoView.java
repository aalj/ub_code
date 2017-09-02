package net.lll0.bus.ui.stationinfo.mvp;

import net.lll0.bus.database.bean.LineInfoBean;
import net.lll0.bus.database.bean.StationInfoBean;
import net.lll0.framwork.mvpbase.view.MvpView;

import java.util.List;

/**
 * Created by stone on 17-7-29.
 */

public interface StationInfoView extends MvpView {

    void getStaticInfo(List<StationInfoBean> stationInfoBeen);


}
