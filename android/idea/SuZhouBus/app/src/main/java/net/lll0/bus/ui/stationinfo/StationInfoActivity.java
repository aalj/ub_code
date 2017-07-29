package net.lll0.bus.ui.stationinfo;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

import net.lll0.bus.ui.stationinfo.mvp.StationInfoPresenter;
import net.lll0.bus.ui.stationinfo.mvp.StationInfoView;
import net.lll0.framwork.support.view.MvpActivity;

/**
 * Created by stone on 17-7-29.
 */

public class StationInfoActivity
        extends MvpActivity<StationInfoView,StationInfoPresenter>
        implements StationInfoView  {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState,
                         @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        initIntent();
    }

    private void initIntent() {

    }

    @Override
    public void getError(String errorMessge) {

    }

    @Override
    public StationInfoPresenter createPresenter() {
        return new StationInfoPresenter(this);
    }
}
