package net.lll0.bus.ui.home.mvp;


import net.lll0.framwork.mvpbase.view.MvpView;
import net.lll0.bus.database.bean.SearchLineBean;

import java.util.List;

/**
 * Created by liangjun on 2017/2/3.
 */

public interface HomeView extends MvpView {

    void getTotalLines(List<SearchLineBean> lineBeens);



}
