package net.lll0.bus.ui.stationinfo.mvp;

import android.content.Context;

import net.lll0.framwork.mvpbase.presenter.impl.MvpBasePresenter;

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
}
