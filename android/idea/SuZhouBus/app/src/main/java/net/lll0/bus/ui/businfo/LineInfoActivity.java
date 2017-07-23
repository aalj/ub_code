package net.lll0.bus.ui.businfo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;


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

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.RequestBody;

public class LineInfoActivity extends MvpActivity<LineView, LinePresenter>
        implements LineView {
    private Activity mActivity;
    private ListView listL_lineinfo = null;
    MyList adapter = null;
    SwipeRefreshLayout swipeRefreshLayout;
    RealTImeInfoEntity realTImeInfoEntity;
    List<LineInfoBean> lineInfoBeens;
    SearchLineBean lineInfoBeen = null;

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
        listL_lineinfo = (ListView) findViewById(R.id.listL_lineinfo);
        adapter = new MyList();
        listL_lineinfo.setAdapter(adapter);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh);
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                //停止刷新
                swipeRefreshLayout.setRefreshing(false);
            }
        });


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
        this.lineInfoBeens = lineInfoBeen;
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
        if (lineInfoBeens != null && realTImeInfoEntity != null) {
            WaitLoading.dismiss();
            buildData(realTImeInfoEntity, lineInfoBeens);
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
        adapter.setData(lineInfoBeen);

    }

    @Override
    public void getError(String errorMessge) {
        WaitLoading.dismiss();
        ToastUtil.showLongToast(mActivity, errorMessge);
    }


    class MyList extends BaseAdapter {
        List<LineInfoBean> list = new ArrayList<>();

        public void setData(List<LineInfoBean> list) {
            if (list != null && list.size() > 0) {
                this.list.clear();
                this.list.addAll(list);
            } else {
                this.list.clear();
            }
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            HolderView holderView = null;
            if (view == null) {
                view = View.inflate(mActivity, R.layout.lineinfo_item, null);
                holderView = new HolderView();
                holderView.stationName = (TextView) view.findViewById(R.id.station_name);
                holderView.stationNum = (TextView) view.findViewById(R.id.station_num);
                holderView.carNumber = (TextView) view.findViewById(R.id.car_number);
                holderView.time = (TextView) view.findViewById(R.id.time);
                view.setTag(holderView);
            } else {
                holderView = (HolderView) view.getTag();
            }

            holderView.stationName.setText(list.get(i).stationName);
            holderView.stationNum.setText(list.get(i).stationNum);
            holderView.carNumber.setText(list.get(i).carNumber);
            holderView.time.setText(list.get(i).time);

            return view;
        }

        class HolderView {
            public TextView stationName;
            public TextView stationNum;
            public TextView carNumber;
            public TextView time;


        }


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
