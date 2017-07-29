package net.lll0.bus.ui.businfo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.support.v7.widget.RecyclerView;


import net.lll0.bus.adapter.BaseRecyclerAdapter;
import net.lll0.bus.adapter.RecyclerViewHolder;
import net.lll0.bus.contstant.BaseConsTent;
import net.lll0.bus.ui.businfo.entity.RealTImeInfoEntity;
import net.lll0.bus.utils.ToastUtil;
import net.lll0.bus.utils.WaitLoading;
import net.lll0.framwork.support.view.MvpActivity;
import net.lll0.bus.suzhoubus.R;
import net.lll0.bus.database.bean.LineInfoBean;
import net.lll0.bus.database.bean.SearchLineBean;
import net.lll0.bus.ui.businfo.mvc.LinePresenter;
import net.lll0.bus.ui.businfo.mvc.LineView;
import net.lll0.bus.ui.home.HomeActivity;
import net.lll0.bus.utils.wight.ToastUtils;

import java.util.List;

import okhttp3.FormBody;
import okhttp3.RequestBody;

public class LineInfoActivity extends MvpActivity<LineView, LinePresenter>
        implements LineView, BaseRecyclerAdapter.OnItemClickListener {

    private static final String TAG=LineInfoActivity.class.getSimpleName();

    private Activity mActivity;
    private RecyclerView listLlineinfo = null;
    SwipeRefreshLayout swipeRefreshLayout;
    RealTImeInfoEntity realTImeInfoEntity;
    List<LineInfoBean> lineInfoBeans;
    SearchLineBean lineInfoBeen = null;
    BaseRecyclerAdapter baseRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mActivity = this;
        initView();
        initIntent();
    }


    private void initView() {
        listLlineinfo = (RecyclerView) findViewById(R.id.listL_lineinfo);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh);
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                //停止刷新
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        listLlineinfo.setItemAnimator(new DefaultItemAnimator());
        listLlineinfo.setLayoutManager(new LinearLayoutManager(this));

        baseRecyclerAdapter = new BaseRecyclerAdapter<LineInfoBean>(mActivity, lineInfoBeans) {
            @Override
            protected int getItemLayoutId(int viewType) {
                return R.layout.item_lineinfo;
            }

            @Override
            protected void bindData(RecyclerViewHolder holder, int position, LineInfoBean item) {
                holder.getView(R.id.lineinfo_index).setBackgroundResource(R.mipmap.bg_round_blue_32);

                holder.setText(R.id.lineinfo_index, item.index);
                holder.setText(R.id.lineinfo_station_name, item.stationName);
                holder.setText(R.id.lineinfo_station_code, item.stationNum);
                holder.setText(R.id.lineinfo_bus_num, item.carNumber);
                holder.setText(R.id.lineinfo_intime, item.time);
                if (!TextUtils.isEmpty(item.time)) {
                    holder.getView(R.id.lineinfo_index).setBackgroundResource(R.mipmap.bg_round_yellow_32);
                }
            }
        };

        if (baseRecyclerAdapter != null) {
            listLlineinfo.setAdapter(baseRecyclerAdapter);
        }

        baseRecyclerAdapter.setOnItemClickListener(this);

    }


    public void initIntent() {


        Intent intent = getIntent();

        if (intent.hasExtra(HomeActivity.LINEINFO_BUNDLE)) {
            Bundle bundleExtra = intent.getBundleExtra(HomeActivity.LINEINFO_BUNDLE);
            lineInfoBeen = (SearchLineBean) bundleExtra.get(HomeActivity.LINEINFO);
        } else {
            finish();
            return;
        }

        if (lineInfoBeen == null) {
            finish();
            return;
        }
        WaitLoading.show(false, mActivity);
        getPresenter().getLineInfo(lineInfoBeen.link);
        RequestBody formBody = new FormBody.Builder()
                .add("lineID", lineInfoBeen.startLineID)
                .build();
        getPresenter().getLineRealTimeInfo(BaseConsTent.HTTP_URL_REAL_TIME, formBody);


    }

    @Override
    public LinePresenter createPresenter() {
        return new LinePresenter(this);
    }

    @Override
    public void getLineInfo(List<LineInfoBean> lineInfoBeen) {
        this.lineInfoBeans = lineInfoBeen;
        if (lineInfoBeen != null && realTImeInfoEntity != null) {
            WaitLoading.dismiss();
            buildData(realTImeInfoEntity, lineInfoBeen);
        }

        RequestBody formBody = new FormBody.Builder()
                .add("lineID", this.lineInfoBeen.startLineID)
                .build();
        getPresenter().getLineRealTimeInfo(BaseConsTent.HTTP_URL_REAL_TIME, formBody);
    }

    @Override
    public void getLineRealTimeInfo(RealTImeInfoEntity realTImeInfoEntity) {
        this.realTImeInfoEntity = realTImeInfoEntity;
        if (lineInfoBeans != null && realTImeInfoEntity != null) {
            WaitLoading.dismiss();
            buildData(realTImeInfoEntity, lineInfoBeans);
        }

    }


    private void buildData(RealTImeInfoEntity realTImeInfoEntity, List<LineInfoBean> lineInfoBeen) {

        for (LineInfoBean lineInfoBean : lineInfoBeen) {
            for (RealTImeInfoEntity.DataBean dataBean : realTImeInfoEntity.getData()) {
                if (lineInfoBean.stationId.equals(dataBean.getID())) {
                    lineInfoBean.time = dataBean.getInTime();
                    lineInfoBean.carNumber = dataBean.getBusInfo();
                    lineInfoBean.stationNum = dataBean.getCode();
                }
            }
        }
        baseRecyclerAdapter.addList(lineInfoBeen);
    }

    @Override
    public void getError(String errorMessge) {
        WaitLoading.dismiss();
        ToastUtil.showLongToast(mActivity, errorMessge);
    }

    @Override
    public void onItemClick(View itemView, int pos) {
        ToastUtil.showLongToast(mActivity,"点击");
        LineInfoBean lineInfoBean = lineInfoBeans.get(pos);
        Log.e(TAG, "onItemClick: " );
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.collection, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.collection) {
            ToastUtils.showShort(this, "点击菜单");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
