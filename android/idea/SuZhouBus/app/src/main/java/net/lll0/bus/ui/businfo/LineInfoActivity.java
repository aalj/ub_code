package net.lll0.bus.ui.businfo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import net.lll0.bus.adapter.BaseRecyclerAdapter;
import net.lll0.bus.adapter.RecyclerViewHolder;
import net.lll0.bus.contstant.BaseConstant;
import net.lll0.bus.database.bean.LineInfoBean;
import net.lll0.bus.database.bean.SearchLineBean;
import net.lll0.bus.database.bean.StationInfoBean;
import net.lll0.bus.database.entity.CollectionBusLineEntity;
import net.lll0.bus.database.manager.CollectionManager;
import net.lll0.bus.suzhoubus.R;
import net.lll0.bus.ui.businfo.entity.RealTImeInfoEntity;
import net.lll0.bus.ui.businfo.mvc.LinePresenter;
import net.lll0.bus.ui.businfo.mvc.LineView;
import net.lll0.bus.ui.home.HomeActivity;
import net.lll0.bus.ui.stationinfo.StationInfoActivity;
import net.lll0.bus.utils.ToastUtil;
import net.lll0.bus.utils.WaitLoading;
import net.lll0.bus.utils.wight.ToastUtils;
import net.lll0.framwork.support.view.MvpActivity;

import java.util.List;

import okhttp3.FormBody;
import okhttp3.RequestBody;

public class LineInfoActivity extends MvpActivity<LineView, LinePresenter>
        implements LineView, BaseRecyclerAdapter.OnItemClickListener {

    private static final String TAG = LineInfoActivity.class.getSimpleName();
    public static final String INTENT_JUMP_STATICINFO = "INTENT_JUMP_STATICINFO";

    private Activity mActivity;
    private RecyclerView listLlineinfo = null;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RealTImeInfoEntity realTImeInfoEntity;
    private List<LineInfoBean> lineInfoBeans;
    private SearchLineBean lineInfoBeen = null;
    private BaseRecyclerAdapter baseRecyclerAdapter;
    //收藏按钮
    private MenuItem collectionBut;
    //数据库操作对象
    private CollectionManager instance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate: ");
        setContentView(R.layout.activity_line_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        instance = CollectionManager.getInstance();
        mActivity = this;
        initView();
        Intent intent = getIntent();
        initIntent(intent);
        iniData();
    }

    private void iniData() {
        Log.e(TAG, "iniData: ");

    }


    private void initView() {
        listLlineinfo = (RecyclerView) findViewById(R.id.listL_lineinfo);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh);
        swipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_light,
                android.R.color.holo_red_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_green_light);
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

    @Override
    protected void onNewIntent(Intent intent) {
        Log.e(TAG, "onNewIntent: " + TAG);
        super.onNewIntent(intent);
        initIntent(intent);
        if (listLlineinfo != null) {
            listLlineinfo.scrollToPosition(0);
        }
    }

    public void initIntent(Intent intent) {


        if (intent.hasExtra(StationInfoActivity.JUMP_LINEINFO_ACTIVITY)) {
            StationInfoBean stationInfoBean = intent.getParcelableExtra(StationInfoActivity.JUMP_LINEINFO_ACTIVITY);
            lineInfoBeen = new SearchLineBean();
            lineInfoBeen.link = stationInfoBean.lineUrl;
            lineInfoBeen.startLineID = stationInfoBean.lineId;

        }

        if (intent.hasExtra(HomeActivity.LINEINFO_BUNDLE)) {
            Bundle bundleExtra = intent.getBundleExtra(HomeActivity.LINEINFO_BUNDLE);
            lineInfoBeen = (SearchLineBean) bundleExtra.get(HomeActivity.LINEINFO);
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
        getPresenter().getLineRealTimeInfo(BaseConstant.HTTP_URL_REAL_TIME, formBody);


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
        getPresenter().getLineRealTimeInfo(BaseConstant.HTTP_URL_REAL_TIME, formBody);
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
//        ToastUtil.showLongToast(mActivity,"点击");
        LineInfoBean lineInfoBean = lineInfoBeans.get(pos);
        Log.e(TAG, "onItemClick: ");

        Intent intent = new Intent(mActivity, StationInfoActivity.class);
        intent.putExtra(INTENT_JUMP_STATICINFO, lineInfoBean);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.collection, menu);
        collectionBut = menu.findItem(R.id.collection);
        Log.e(TAG, "onCreateOptionsMenu: ");

        CollectionBusLineEntity byId = instance.findById(lineInfoBeen.startLineID);
        if (byId != null) {
//            collectionBut.setEnabled(false);
            collectionBut.setIcon(ContextCompat.getDrawable(mActivity, R.mipmap.collection_targer_select));
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Log.e(TAG, "onOptionsItemSelected: ");

        if (id == R.id.collection) {
//            ToastUtils.showShort(this, "正在点击");
            CollectionBusLineEntity byId = instance.findById(lineInfoBeen.startLineID);
            if (byId == null) {//点击收藏公交数据
                CollectionBusLineEntity collectionBusLineEntity = new CollectionBusLineEntity();
                collectionBusLineEntity.setId(lineInfoBeen.startLineID);
                collectionBusLineEntity.setEndLine(lineInfoBeen.endLineID);
                collectionBusLineEntity.setUrl(lineInfoBeen.link);
                collectionBusLineEntity.setLineName(lineInfoBeen.lineName);
                instance.insert(collectionBusLineEntity);
                ToastUtils.showShort(this, "收藏成功");
//                collectionBut.setEnabled(false);
                collectionBut.setIcon(ContextCompat.getDrawable(mActivity, R.mipmap.collection_targer_select));

            } else {//取消收藏
                instance.delete(byId);
                ToastUtils.showShort(this, "取消收藏");
                collectionBut.setIcon(ContextCompat.getDrawable(mActivity, R.mipmap.collection_targer));
            }

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        Log.i(TAG, "onPrepareOptionsMenu: 加载菜单按钮");
        return true;
    }
}
