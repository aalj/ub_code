package net.lll0.bus.ui.stationinfo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.lll0.bus.adapter.RecyclerViewHolder;
import net.lll0.bus.adapter.RecyclerViewNotHeadFootAdapter;
import net.lll0.bus.database.bean.LineInfoBean;
import net.lll0.bus.database.bean.StationInfoBean;
import net.lll0.bus.suzhoubus.R;
import net.lll0.bus.ui.businfo.LineInfoActivity;
import net.lll0.bus.ui.stationinfo.mvp.StationInfoPresenter;
import net.lll0.bus.ui.stationinfo.mvp.StationInfoView;
import net.lll0.bus.utils.ObjectUtils;
import net.lll0.bus.utils.ResourcesUtils;
import net.lll0.bus.utils.ToastUtil;
import net.lll0.bus.utils.WaitLoading;
import net.lll0.bus.utils.wight.ToastUtils;
import net.lll0.framwork.support.view.MvpActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stone on 17-7-29.
 */

public class StationInfoActivity
        extends MvpActivity<StationInfoView, StationInfoPresenter>
        implements StationInfoView, RecyclerViewNotHeadFootAdapter.OnItemClickListener {


    private static final String TAG = StationInfoActivity.class.getSimpleName();
    public static String JUMP_LINEINFO_ACTIVITY = "JUMP_LINEINFO_ACTIVITY";
    private Toolbar mToolbar;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout mRefresh;
    private RecyclerViewNotHeadFootAdapter<StationInfoBean> adapter = null;
    private List<StationInfoBean> stationInfoBeen = null;
    private Activity mActivity = null;
    private LineInfoBean lineInfoBean = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_info);
        mActivity = this;
        initView();

        myFinish(mToolbar);
        initIntent();
    }

    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.listL_lineinfo);
        mRefresh = (SwipeRefreshLayout) findViewById(R.id.refresh);
        stationInfoBeen = new ArrayList<>();

        mRefresh.setColorSchemeResources(
                android.R.color.holo_blue_light,
                android.R.color.holo_red_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_green_light
        );

        mRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                getDefaultData(lineInfoBean,true);

            }
        });
        adapter = new RecyclerViewNotHeadFootAdapter<StationInfoBean>(stationInfoBeen, mActivity) {
            @Override
            protected RecyclerViewHolder myCreateViewHolder(ViewGroup parent, int viewType) {
                return new RecyclerViewHolder(mActivity, LayoutInflater.from(mActivity).inflate(R.layout.item_station_info, parent, false), viewType);
            }

            @Override
            protected void myBindViewHolder(RecyclerViewHolder holder, final int position) {
                //设置公交线路
                StationInfoBean stationInfoBean = stationInfoBeen.get(position);
                holder.setText(R.id.bus_line_number_te, stationInfoBean.lineName);
                View stationNum01 = holder.getView(R.id.station_num_1);
                View stationNnm03 = holder.getView(R.id.station_num_3);

                String realTimeInfo = stationInfoBean.realTimeInfo;
                if ("尚未驶离首发站".equals(realTimeInfo)) {
                    stationNum01.setVisibility(View.GONE);
                    stationNnm03.setVisibility(View.GONE);
                } else if (ResourcesUtils.getString(mActivity, R.string.data_lost).equals(realTimeInfo)) {//实时数据失联
                    stationNum01.setVisibility(View.GONE);
                    stationNnm03.setVisibility(View.GONE);
                    ((TextView) holder.getView(R.id.station_num_2)).setTextColor(ResourcesUtils.getColor(mActivity, R.color.customize_red));

                } else {
                    stationNum01.setVisibility(View.VISIBLE);
                    stationNnm03.setVisibility(View.VISIBLE);
                }
                holder.setText(R.id.station_num_2, realTimeInfo);


                holder.setText(R.id.station_start_end_content, stationInfoBean.startStation);

                holder.getView(R.id.station_collection).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        ToastUtils.showShort(mActivity, "收藏功能正在开发");
                    }
                });
            }
        };

        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(this);


    }

    private void initIntent() {

        Intent intent = getIntent();

        if (intent.hasExtra(LineInfoActivity.INTENT_JUMP_STATICINFO)) {
            lineInfoBean = intent.getParcelableExtra(LineInfoActivity.INTENT_JUMP_STATICINFO);
        } else {
            finish();
        }

        if (lineInfoBean == null) {
            finish();
        }
        getDefaultData(lineInfoBean,false);

    }

    private void getDefaultData(LineInfoBean lineInfoBean,boolean isPull) {
        if (lineInfoBean!=null) {
            if (!isPull) {
                WaitLoading.show(false, mActivity);
            }
            //显示等待动画
            getPresenter().getStationInfo(lineInfoBean.lineUrl);
        }else{
            finish();
        }

    }

    @Override
    public void getError(String errorMessge) {
        WaitLoading.dismiss();
        ToastUtil.showLongToast(mActivity, errorMessge);
    }

    @Override
    public StationInfoPresenter createPresenter() {
        return new StationInfoPresenter(this);
    }

    @Override
    public void getStaticInfo(List<StationInfoBean> stationInfoBeen) {
        WaitLoading.dismiss();
        Log.e(TAG, "getStaticInfo: ");
        adapter.addList(stationInfoBeen);
        //停止刷新
        mRefresh.setRefreshing(false);


    }


    @Override
    public void onItemClick(View itemView, int pos) {

        StationInfoBean stationInfoBean = stationInfoBeen.get(pos);
        Intent intent = new Intent(mActivity,LineInfoActivity.class);
        intent.putExtra(JUMP_LINEINFO_ACTIVITY,stationInfoBean);
            startActivity(intent);
    }
}
