package com.wdsunday.ui;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.Layout;
import android.view.*;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import com.wdsunday.R;
import com.wdsunday.database.ParsesHomeData;
import com.wdsunday.database.bean.LineBean;
import com.wdsunday.http.HttpManger;
import com.wdsunday.http.SendData;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    private Button but = null;
    private ListView list = null;
    private Activity mActivity;
    private MyList adapter =null;
    private List<LineBean> lineBeans = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mActivity = this;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


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
        if(R.id.but_home==id){
            HttpManger.useOkHttp(new SendData() {
                @Override
                public void sendString(String data) {
//                    System.out.println(data);
                     lineBeans = new ParsesHomeData(data).parseHtml();
                    Message msg = new Message();
                    msg.what = 0x0110;
                    msg.obj=lineBeans;
                    handler.sendMessage(msg);

                }
            });
        }
    }

    private void initView() {
        but = (Button) findViewById(R.id.but_home);
        but.setOnClickListener(this);

        list = (ListView)findViewById(R.id.list);
        adapter = new MyList();
        list.setAdapter(adapter);

    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            int what = msg.what;
            if(0x0110==what){
                List<LineBean> list= (List<LineBean>) msg.obj;
                adapter.setData(list);
            }


            super.handleMessage(msg);
        }
    };




    class MyList extends BaseAdapter{
        List<LineBean> list = new ArrayList<>();

        public void setData(List<LineBean> list){
            if (list != null && list.size() > 0) {
               this. list.clear();
                this.list.addAll(list);
                notifyDataSetChanged();
            }
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
            if(view == null){
                view = View.inflate(mActivity,R.layout.item,null);
                holderView = new HolderView();
                holderView.lineName = (TextView) view.findViewById(R.id.textView3);
                holderView.staticName = (TextView) view.findViewById(R.id.textView2);
                view.setTag(holderView);
            }else{
                holderView = (HolderView) view.getTag();
            }

            holderView.lineName.setText(list.get(i).lineName);
            holderView.staticName.setText(list.get(i).pathName);

            return view;
        }

        class HolderView{
            public TextView lineName ;
            public TextView staticName ;


        }


    }






}
