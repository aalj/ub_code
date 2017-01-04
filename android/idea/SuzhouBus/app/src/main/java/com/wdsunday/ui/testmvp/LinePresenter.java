package com.wdsunday.ui.testmvp;

import android.app.Activity;
import android.content.Context;

import com.wdsunday.database.ParsesHomeData;
import com.wdsunday.database.bean.LineInfoBean;
import com.wdsunday.framework.base.presenter.impl.MvpBasePresenter;
import com.wdsunday.http.SendData;

import java.util.List;
import java.util.TimerTask;

/**
 * Created by stone on 17-1-3.
 */

public class LinePresenter extends MvpBasePresenter<LineView> {

    private LineModel lineModel;
    private Context context;


    public LinePresenter(Context context) {
        super(context);
        this.lineModel = new LineModel();
        this.context = context;
    }


}
