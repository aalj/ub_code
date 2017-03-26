package com.wdsunday.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.wdsunday.bus.R;
import com.wdsunday.database.bean.LineInfoBean;
import com.wdsunday.ui.home.HomeActivity;

import java.util.ArrayList;
import java.util.List;

public class LineInfoActivity extends AppCompatActivity{
    private Activity mActivity;
    private ListView listL_lineinfo  = null;
    MyList adapter = null;

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
    }


    public void initIntent(){
        Intent intent = getIntent();
            List<LineInfoBean> lineInfoBeen = null;
        if(intent.hasExtra(HomeActivity.LINEINFO_BUNDLE)){
            Bundle bundleExtra = intent.getBundleExtra(HomeActivity.LINEINFO_BUNDLE);
            lineInfoBeen  = (List<LineInfoBean>) bundleExtra.get(HomeActivity.LINEINFO);
        }else{
            finish();
            return;
        }

        if(lineInfoBeen== null&& lineInfoBeen.size()<=0){
            finish();
            return;
        }
        adapter.setData(lineInfoBeen);


    }

    class MyList extends BaseAdapter {
        List<LineInfoBean> list = new ArrayList<>();

        public void setData(List<LineInfoBean> list) {
            if (list != null && list.size() > 0) {
                this.list.clear();
                this.list.addAll(list);
            }else{
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
}
