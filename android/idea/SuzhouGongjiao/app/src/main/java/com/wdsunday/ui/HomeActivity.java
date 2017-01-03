package com.wdsunday.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.Layout;
import android.text.TextUtils;
import android.util.Log;
import android.view.*;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.wdsunday.R;
import com.wdsunday.database.ParsesHomeData;
import com.wdsunday.database.bean.LineInfoBean;
import com.wdsunday.database.bean.SearchLineBean;
import com.wdsunday.framework.base.view.impl.MvpActivity;
import com.wdsunday.http.HttpManger;
import com.wdsunday.http.SendData;
import com.wdsunday.ui.testmvp.TotalPresenter;
import com.wdsunday.ui.testmvp.TotalView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class HomeActivity extends MvpActivity<TotalView, TotalPresenter>
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener, TotalView, AdapterView.OnItemClickListener {
    private Button but = null;
    private ListView list = null;
    private Activity mActivity;
    private MyList adapter = null;
    private List<SearchLineBean> lineBeans = null;
    private EditText lineNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mActivity = this;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();
        Log.e("Stone", "1---"+Thread.currentThread().getName() );

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }

    @Override
    public TotalPresenter createPresenter() {
        return new TotalPresenter(this);
    }

    @Override
    public TotalView createView() {
        return this;
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (R.id.but_home == id) {

            String lineNumStr = lineNum.getText().toString().trim();
            if (!TextUtils.isEmpty(lineNumStr)) {
                getPresenter().getTotalLines(lineNumStr);
            } else {
                Toast.makeText(mActivity, "输入公交番号", Toast.LENGTH_SHORT).show();
            }

        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        getPresenter().getLineInfo(lineBeans.get(position).link);
    }

    private void initView() {
        but = (Button) findViewById(R.id.but_home);
        but.setOnClickListener(this);

        list = (ListView) findViewById(R.id.list);
        adapter = new MyList();
        list.setAdapter(adapter);
        list.setOnItemClickListener(this);

        lineNum = (EditText) findViewById(R.id.linenum);

    }


    @Override
    public void getTotalLines(List<SearchLineBean> lineBeens) {

        this.lineBeans = lineBeens;
        Toast.makeText(mActivity,lineBeans.size()+"",Toast.LENGTH_SHORT).show();

        Log.e("Stone", Thread.currentThread().getName() );

        Toast.makeText(mActivity,lineBeens.size()+"",Toast.LENGTH_SHORT).show();

        adapter.setData(lineBeens);
//        Message msg = new Message();
//        msg.what = 0x0110;
//        msg.obj = lineBeens;
//        handler.sendMessage(msg);
    }

    @Override
    public void getLineInfo(List<LineInfoBean> lineInfoBeen) {
        Intent intent = new Intent(mActivity, LineInfoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("lineinfo", (Serializable) lineInfoBeen);
        startActivity(intent);
    }


    class MyList extends BaseAdapter {
        List<SearchLineBean> list = new ArrayList<>();

        public void setData(List<SearchLineBean> list) {
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
                view = View.inflate(mActivity, R.layout.item, null);
                holderView = new HolderView();
                holderView.lineName = (TextView) view.findViewById(R.id.textView3);
                holderView.staticName = (TextView) view.findViewById(R.id.textView2);
                view.setTag(holderView);
            } else {
                holderView = (HolderView) view.getTag();
            }

            holderView.lineName.setText(list.get(i).lineName);
            holderView.staticName.setText(list.get(i).pathName);

            return view;
        }

        class HolderView {
            public TextView lineName;
            public TextView staticName;


        }


    }


}
