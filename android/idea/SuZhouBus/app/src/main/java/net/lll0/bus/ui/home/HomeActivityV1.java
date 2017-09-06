package net.lll0.bus.ui.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import net.lll0.bus.adapter.BaseRecyclerAdapter;
import net.lll0.bus.adapter.RecyclerViewHolder;
import net.lll0.bus.contstant.UmengConstant;
import net.lll0.bus.database.bean.SearchLineBean;
import net.lll0.bus.suzhoubus.R;
import net.lll0.bus.ui.businfo.LineInfoActivity;
import net.lll0.bus.ui.feedback.FeedbackActivity;
import net.lll0.bus.ui.home.mvp.HomePresenter;
import net.lll0.bus.ui.home.mvp.HomeView;
import net.lll0.bus.utils.ToastUtil;
import net.lll0.bus.utils.WaitLoading;
import net.lll0.bus.utils.umeng.UmengManger;
import net.lll0.framwork.support.view.MvpActivity;
import net.youmi.android.AdManager;
import net.youmi.android.nm.bn.BannerManager;
import net.youmi.android.nm.bn.BannerViewListener;
import net.youmi.android.nm.sp.SpotListener;
import net.youmi.android.nm.sp.SpotManager;
import net.youmi.android.nm.sp.SpotRequestListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static android.view.View.GONE;

public class HomeActivityV1 extends MvpActivity<HomeView, HomePresenter>
        implements
        View.OnClickListener, HomeView {
    private static final String TAG = HomeActivityV1.class.getSimpleName();


    private Activity mActivity;
    private List<SearchLineBean> lineBeans = null;

    private EditText lineNum;
    private LinearLayout searchIconLay = null;
    private LinearLayout searchActionLay = null;
    private ImageView searchTextDelete = null;
    private TextView searchBtnAction = null;
    private RecyclerView list = null;
    private TextView mFeedback;

    //    CardView cardView;
    BaseRecyclerAdapter<SearchLineBean> recyclerAdapter = null;


    //用于Intent数据传递
    public static final String LINEINFO = "lineinfo";
    public static final String LINEINFO_BUNDLE = "lineinfo_bundle";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_home_v1);

        AdManager.getInstance(this).init("35b77a7c2db7cac1", "72ac7972f128b2f2", true  );
//        AdManager.getInstance(this).init("0ff2ce00fbaadac6", "4b17d630489c5c7a", true  );


        mActivity = this;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();
//        initData();


    }

    private void initData() {
        SpotManager.getInstance(mActivity).setImageType(SpotManager.IMAGE_TYPE_VERTICAL);

        // 设置动画类型，默认高级动画
        //		// 无动画
        //		SpotManager.getInstance(mContext).setAnimationType(SpotManager
        //				.ANIMATION_TYPE_NONE);
        //		// 简单动画
        //		SpotManager.getInstance(mContext)
        //		                    .setAnimationType(SpotManager.ANIMATION_TYPE_SIMPLE);
        // 高级动画
        SpotManager.getInstance(mActivity)
                .setAnimationType(SpotManager.ANIMATION_TYPE_ADVANCED);

        SpotManager.getInstance(mActivity).requestSpot(new SpotRequestListener() {
            @Override
            public void onRequestSuccess() {
                Log.e(TAG, "onRequestSuccess:  广告获取成功" );
                ToastUtil.showShortToast(mActivity,"广澳获取成功");

//                SpotManager.getInstance(mActivity).showSpot(mActivity,
//                        new SpotListener() {
//                            @Override
//                            public void onShowSuccess() {
//                                Log.e(TAG, "onRequestSuccess:  展示成功" );
//                            }
//
//                            @Override
//                            public void onShowFailed(int i) {
//                                Log.e(TAG, "onRequestSuccess:  展示失败" );
//                            }
//
//                            @Override
//                            public void onSpotClosed() {
//                                Log.e(TAG, "onRequestSuccess:  展示关闭" );
//                            }
//
//                            @Override
//                            public void onSpotClicked(boolean b) {
//                                Log.e(TAG, "onRequestSuccess:  展示点击" );
//                            }
//                        });

                SpotManager.getInstance(mActivity).showSlideableSpot(mActivity,
                        new SpotListener() {
                            @Override
                            public void onShowSuccess() {
                                Log.e(TAG, "onRequestSuccess:  展示成功" );
                            }

                            @Override
                            public void onShowFailed(int i) {
                                Log.e(TAG, "onRequestSuccess:  展示失败" );
                            }

                            @Override
                            public void onSpotClosed() {
                                Log.e(TAG, "onRequestSuccess:  展示关闭" );
                            }

                            @Override
                            public void onSpotClicked(boolean b) {
                                Log.e(TAG, "onRequestSuccess:  展示点击" );
                            }
                        });
            }

            @Override
            public void onRequestFailed(int i) {
                Log.e(TAG, "onRequestSuccess:  广告获取失败" );

            }
        });


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
//        cardView = (CardView) findViewById(R.id.college);
        lineNum = (EditText) findViewById(R.id.linenum);

        list = (RecyclerView) findViewById(R.id.list);

        //对控件进行隐藏和显示
        setSearchShowOrHint(true);

        //初始化控件的显示与隐藏
        searchTextDelete.setVisibility(GONE);
        searchBtnAction.setVisibility(GONE);


//        cardView.setVisibility(View.VISIBLE);
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
                            .hideSoftInputFromWindow(HomeActivityV1.this.getCurrentFocus()
                                    .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    //调用View 的自点击方法
                    searchBtnAction.performClick();
                    return true;
                }
                return false;
            }
        });
        mFeedback = (TextView) findViewById(R.id.feedback);
        mFeedback.setOnClickListener(this);


        View bannerView = BannerManager.getInstance(mActivity)
                .getBannerView(mActivity, new BannerViewListener() {
                    @Override
                    public void onRequestSuccess() {
                        Log.e(TAG, "onRequestSuccess:  展示成功" );
                    }

                    @Override
                    public void onSwitchBanner() {
                        Log.e(TAG, "onRequestSuccess:  onSwitchBanner" );

                    }

                    @Override
                    public void onRequestFailed() {
                        Log.e(TAG, "onRequestSuccess:  展示失败" );
                    }
                });

// 获取要嵌入广告条的布局
        LinearLayout bannerLayout = (LinearLayout) findViewById(R.id.ll_banner);

// 将广告条加入到布局中
        bannerLayout.addView(bannerView);



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
        Log.e(TAG, "onClick: " );
        int id = view.getId();
        if (R.id.home_search_btn_action == id) {  //搜索触发
//            shapeLoadingDialog.show();

            search();

        } else if (R.id.home_search_icon_lay == id) {//显示搜索框
            setSearchShowOrHint(false);

        } else if (R.id.search_text_delete == id) {//删除文字按钮
            lineNum.setText("");

        }else if(R.id.feedback==id){
            Intent intent = new Intent(mActivity, FeedbackActivity.class);
            startActivity(intent);
        }
    }

    private void search() {
        UmengManger.getInstance().onEvent(UmengConstant.HOMEACTIVITY_EVENT_KEY, UmengConstant.HOMEACTIVITY_QUERY_KEY);
        String lineNumStr = lineNum.getText().toString().trim();
        if (!TextUtils.isEmpty(lineNumStr)) {
            WaitLoading.show(false, mActivity);
            getPresenter().getTotalLines("line.php?line=" + lineNumStr);
//            Toast.makeText(mActivity, "开始搜索", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(mActivity, "输入公交番号", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getTotalLines(List<SearchLineBean> lineBeens) {
        WaitLoading.dismiss();

//        cardView.setVisibility(View.GONE);
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
        ToastUtil.showLongToast(mActivity, errorMessge);

    }


    @Override
    protected void onPause() {
        super.onPause();
        // 插屏广告
        SpotManager.getInstance(mActivity).onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        // 插屏广告
        SpotManager.getInstance(mActivity).onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 插屏广告
        SpotManager.getInstance(mActivity).onDestroy();
    }
}
