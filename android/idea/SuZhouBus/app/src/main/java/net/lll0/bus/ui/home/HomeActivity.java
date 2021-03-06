package net.lll0.bus.ui.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import net.lll0.bus.contstant.UmengConstant;
import net.lll0.bus.utils.ToastUtil;
import net.lll0.bus.utils.WaitLoading;
import net.lll0.bus.utils.umeng.UmengManger;
import net.lll0.framwork.support.view.MvpActivity;
import net.lll0.bus.suzhoubus.R;
import net.lll0.bus.adapter.BaseRecyclerAdapter;
import net.lll0.bus.adapter.RecyclerViewHolder;
import net.lll0.bus.database.bean.SearchLineBean;
import net.lll0.bus.ui.businfo.LineInfoActivity;
import net.lll0.bus.ui.home.mvp.HomePresenter;
import net.lll0.bus.ui.home.mvp.HomeView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static android.view.View.GONE;

public class HomeActivity extends MvpActivity<HomeView, HomePresenter>
        implements NavigationView.OnNavigationItemSelectedListener,
        View.OnClickListener, HomeView {
    private Activity mActivity;
    private List<SearchLineBean> lineBeans = null;

    private EditText lineNum;
    private LinearLayout searchIconLay = null;
    private LinearLayout searchActionLay = null;
    private ImageView searchTextDelete = null;
    private TextView searchBtnAction = null;
    private RecyclerView list = null;

    CardView cardView;
    BaseRecyclerAdapter<SearchLineBean> recyclerAdapter = null;


    //用于Intent数据传递
    public static final String LINEINFO = "lineinfo";
    public static final String LINEINFO_BUNDLE = "lineinfo_bundle";

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

// TODO: 2017/4/3 转移
//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        getPresenter().getLineInfo(lineBeans.get(position).link);
//    }

    private void initView() {
        /*
        控件的初始化
        1. 使用的控件
        2. 不同控件在不同时候的出发
        3. 文字输入的监听
         */

        //初始刷控件
        searchIconLay = (LinearLayout) findViewById(R.id.home_search_icon_lay);
        searchActionLay = (LinearLayout) findViewById(R.id.search_action_lay);
        searchTextDelete = (ImageView) findViewById(R.id.search_text_delete);
        searchBtnAction = (TextView) findViewById(R.id.home_search_btn_action);
        cardView = (CardView) findViewById(R.id.college);
        lineNum = (EditText) findViewById(R.id.linenum);

        list = (RecyclerView) findViewById(R.id.list);

        //对控件进行隐藏和显示
        setSearchShowOrHint(true);

        //初始化控件的显示与隐藏
        searchTextDelete.setVisibility(GONE);
        searchBtnAction.setVisibility(GONE);


        cardView.setVisibility(View.VISIBLE);
        list.setVisibility(View.GONE);

        lineNum.addTextChangedListener(textWatcher);


        // 设置控件的点击监听
        searchIconLay.setOnClickListener(this);
        searchTextDelete.setOnClickListener(this);
        searchBtnAction.setOnClickListener(this);

        //初始化RecyclerView的加载数据形式

        //1. 设置Item的加载显示动画
        //2. 设置Item的排列布局
        //3. 设置显示是数据的显示形式


        List<SearchLineBean> value = new ArrayList<>();
        list.setItemAnimator(new DefaultItemAnimator());
        list.setLayoutManager(new LinearLayoutManager(this));
        recyclerAdapter = new BaseRecyclerAdapter<SearchLineBean>(mActivity, value) {
            @Override
            protected int getItemLayoutId(int viewType) {
                return R.layout.item;
            }

            @Override
            protected void bindData(RecyclerViewHolder holder, int position, SearchLineBean item) {
                holder.setText(R.id.textView3, item.lineName);
                holder.setText(R.id.textView2, item.pathName);

            }
        };
        if (recyclerAdapter != null) {
            list.setAdapter(recyclerAdapter);
        }
        recyclerAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int pos) {

                // TODO: 2017/4/3 换成页面跳转
                Intent intent = new Intent(mActivity, LineInfoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(LINEINFO, (Serializable) lineBeans.get(pos));
                intent.putExtra(LINEINFO_BUNDLE, bundle);
                startActivity(intent);

//                getPresenter().getLineInfo(lineBeans.get(pos).link);
            }
        });


        lineNum.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() != KeyEvent.ACTION_UP) {
                    // 先隐藏键盘
                    ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(HomeActivity.this.getCurrentFocus()
                                    .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    //调用View 的自点击方法
                    searchBtnAction.performClick();
                    return true;
                }
                return false;
            }
        });
    }


    //设置触发搜索框显示的图标
    private void setSearchShowOrHint(boolean searchIconShowOrHint) {
        if (searchIconShowOrHint) {
            searchIconLay.setVisibility(View.VISIBLE);
            searchActionLay.setVisibility(GONE);
        } else {
            searchIconLay.setVisibility(GONE);
            searchActionLay.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (R.id.home_search_btn_action == id) {  //搜索触发
//            shapeLoadingDialog.show();

            search();

        } else if (R.id.home_search_icon_lay == id) {//显示搜索框
            setSearchShowOrHint(false);

        } else if (R.id.search_text_delete == id) {//删除文字按钮
            lineNum.setText("");

        }
    }

    private void search() {
        UmengManger.getInstance().onEvent(UmengConstant.HOMEACTIVITY_EVENT_KEY,UmengConstant.HOMEACTIVITY_QUERY_KEY);
        String lineNumStr = lineNum.getText().toString().trim();
        if (!TextUtils.isEmpty(lineNumStr)) {
            WaitLoading.show(false,mActivity);
            getPresenter().getTotalLines("line.php?line="+lineNumStr);
            Toast.makeText(mActivity, "开始搜索", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(mActivity, "输入公交番号", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getTotalLines(List<SearchLineBean> lineBeens) {
        WaitLoading.dismiss();

        cardView.setVisibility(View.GONE);
        list.setVisibility(View.VISIBLE);

        this.lineBeans = lineBeens;
        recyclerAdapter.addList(lineBeens);
    }



    @Override
    public HomePresenter createPresenter() {
        return new HomePresenter(this);
    }


    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            // TODO: 2017/2/4 在这里进行处理显示
            if (TextUtils.isEmpty(s.toString())) {
                searchTextDelete.setVisibility(View.GONE);
                searchBtnAction.setVisibility(View.GONE);
            } else {
                searchTextDelete.setVisibility(View.VISIBLE);
                searchBtnAction.setVisibility(View.VISIBLE);

            }

        }
    };


    @Override
    public void getError(String errorMessge) {
        WaitLoading.dismiss();
        ToastUtil.showLongToast(mActivity,errorMessge);

    }
}
