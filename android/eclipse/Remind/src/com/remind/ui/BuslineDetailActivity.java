package com.remind.ui;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.baidu.mapapi.search.busline.BusLineResult.BusStation;
import com.mr.remind.R;
import com.remind.adapter.BusLineStationsAdapter;
import com.remind.baidu.BusLineHelper;
import com.remind.baidu.BusLineHelper.OnBusLineSearchListener;
import com.remind.bean.MrAlarm;
import com.remind.bean.MrBusLine;
import com.remind.bean.TransferData;
import com.remind.bean.TransferData.DataType;
import com.remind.ui.base.BaseActivity;
import com.remind.ui.fragment.RemindFragment;
import com.remind.utils.LogUtils;
import com.remind.view.dialog.DialogTips;
import com.remind.widget.HeaderLayout.onRightImageButtonClickListener;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

public class BuslineDetailActivity extends BaseActivity implements OnBusLineSearchListener,
	OnItemLongClickListener,OnItemClickListener{

	public static final String TAG = "BusLineDetailActivity";
	/**
	 * 该Activity显示此公交路线;
	 */
	private MrBusLine 	mBusLine=null;
	private MrBusLine 	mReverseBusLine=null;
	private ListView 	mListView;
	private ScrollView  mScrollView;
	private TextView 	mDrectionTextView;
	private TextView 	mTimeTextView;
	private ProgressDialog 	mProgressDialog;
	private BusLineHelper 	mBusLineHelper;
	private BusLineStationsAdapter mStationsAdapter;
	
	private final int BUSLINE_FLAG=0;		//正向公交数据;
	private final int RE_BUSLINE_FLAG=1;	//反向公交数据;
	
	private int mSearchFlag;				//正反向公交的搜索标志;
	private int mShowFlag;					//正反向公交的数据显示标志;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_busline_detail);

		initView();
		initData();
	}

	private void initView(){
		
		//获取数据;
		Bundle extras=getIntent().getExtras();
		String busName=extras.getString("busName");
		String uid=extras.getString("buslineUid");
		String reverseUid=extras.getString("reverseBuslineUid");
		
		mBusLine=new MrBusLine();
		mReverseBusLine=new MrBusLine();
		mBusLine.setBusName(busName);
		mReverseBusLine.setBusName(busName);
		mBusLine.setUid(uid);
		mReverseBusLine.setUid(reverseUid);
		
		//初始化界面;
		initTopBarForBoth(busName, R.drawable.base_action_bar_baidu_bg_selector,
				new onBaiDuMapButtonClickListener(this));
		
		mListView=(ListView)findViewById(R.id.lv_busline_detail_stations);
		mDrectionTextView=(TextView)findViewById(R.id.tv_busline_detail_drection);
		mTimeTextView=(TextView)findViewById(R.id.tv_busline_detail_time);
		mScrollView=(ScrollView) findViewById(R.id.sv_busline_detail);
		
		mListView.setOnItemLongClickListener(this);
		mListView.setOnItemClickListener(this);
		registerForContextMenu(mListView);
		
		mStationsAdapter=new BusLineStationsAdapter(this, null);
		mListView.setAdapter(mStationsAdapter);
	}
	
	public void initData(){
		
		//开始查找;
		mBusLineHelper=new BusLineHelper();
		mBusLineHelper.setMrBusLineSearchListener(this);
		mBusLineHelper.init();
		
		mProgressDialog = new ProgressDialog(this);
		mProgressDialog.setCanceledOnTouchOutside(true);
		
		mShowFlag=RE_BUSLINE_FLAG;
		busLineSearch();
	}
	
	/**
	 * 点击右边按钮显示百度地图的路线;
	 * @author Render;
	 */
	public class onBaiDuMapButtonClickListener implements onRightImageButtonClickListener{

		private Context ct;
		
		public onBaiDuMapButtonClickListener(Context ct){
			this.ct=ct;
		}
		
		@Override
		public void onClick() {
			
			try {
				if(mShowFlag==BUSLINE_FLAG){
					//设置当前的busLine为全局app对象中的busline;
					TransferData.getInstance().setData(mReverseBusLine, DataType.BUSLINE);
				}else if(mShowFlag==RE_BUSLINE_FLAG){
					TransferData.getInstance().setData(mBusLine, DataType.BUSLINE);
				}
						
				Intent intent=new Intent(ct, BuslineBaiduMapActivity.class);
				ct.startActivity(intent);
				
			} catch (Exception e) {
				
				LogUtils.i(TAG, e.getMessage());
				e.printStackTrace();
			}
		}
	}

	public void onTabSelect(View v) {
		
		int id=v.getId();
		
		switch(id){
		
			case R.id.btn_reverse_busline:{
				onBtnReverseBusline();
				break;
			}
			case R.id.btn_sleep_plan:{
				redictToActivity(this, RemindActivity.class, null);
				break;
			}
		}
	}
	
	/**
	 * 切换到返程路线;
	 */
	private void onBtnReverseBusline(){
		
		if(mShowFlag==BUSLINE_FLAG){
			busLineSearch();
			mShowFlag=RE_BUSLINE_FLAG;
			
			UpdateView(mBusLine);
			
		}else if(mShowFlag==RE_BUSLINE_FLAG){
			reverseBusLineSearch();
			mShowFlag=BUSLINE_FLAG;
			
			UpdateView(mReverseBusLine);
		}
	}

	@SuppressLint("SimpleDateFormat")
	private void UpdateView(MrBusLine mrbusline){
		
		if(mrbusline.getBusLineResult()==null){
			return;
		}
		
		List<BusStation> busStations=mrbusline.getBusLineResult().getStations();
		
		mStationsAdapter.updateListView(busStations);

		String startStation=busStations.get(0).getTitle();
		String lastStation=busStations.get(busStations.size()-1).getTitle();
		
		String drection=startStation+"-->"+lastStation;
		mDrectionTextView.setText(drection);
		
		Date beginDate=mrbusline.getBusLineResult().getStartTime();
		Date endDate=mrbusline.getBusLineResult().getEndTime();
		
		DateFormat df = new SimpleDateFormat("HH:mm");
		String beginTime=df.format(beginDate);
		String endTime=df.format(endDate);
		
		String time="首末班时间："+beginTime+"--"+endTime;
		
		mTimeTextView.setText(time);
		
		//手动设置滚动条滚到最上方;
		mScrollView.smoothScrollTo(0, 0);
	}

	@Override
	public void onGetBusLineResult(MrBusLine busLine) {
		
		mProgressDialog.dismiss();
		
		if(busLine==null){
			return;
		}
		
		if(mSearchFlag==BUSLINE_FLAG){
			
			mBusLine.setBusLineResult(busLine.getBusLineResult());
			mBusLine.setUid(busLine.getUid());
				
		}else if(mSearchFlag==RE_BUSLINE_FLAG){
		
			mReverseBusLine.setBusLineResult(busLine.getBusLineResult());
			mReverseBusLine.setUid(busLine.getUid());
		}
		UpdateView(busLine);
	}
	
	/**
	 * 正向公交搜索;
	 */
	private void busLineSearch(){
		
		mSearchFlag=BUSLINE_FLAG;
		if(mBusLine.getUid()==null){
			
			mProgressDialog.show();
			mBusLineHelper.searchBusLineByName(mBusLine.getBusName(),null);
			return;
		}
		//如果没有搜索,则进行搜索,否则直接返回;
		else if(mBusLine.getBusLineResult()==null){
			
			mProgressDialog.show();
			mBusLineHelper.searchBusLineByUid(mBusLine.getUid());
			return;
		}
	}
	/**
	 * 反向公交搜索,此时正向公交一定已经搜索到了;
	 */
	private void reverseBusLineSearch(){
		
		mSearchFlag=RE_BUSLINE_FLAG;
		if(mReverseBusLine.getUid()==null){
			
			mProgressDialog.show();
			mBusLineHelper.searchBusLineByName(mReverseBusLine.getBusName(),mBusLine.getUid());
			
			return;
		}
		else if(mReverseBusLine.getBusLineResult()==null){
			
			mProgressDialog.show();
			mBusLineHelper.searchBusLineByUid(mReverseBusLine.getUid());
			
			return;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		
	}
	
	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view,
			int position, long id) {
		
		//获得当前的站点;
		BusStation busStation=(BusStation) mStationsAdapter.getItem(position);
		
		showAddAlarmDialog(busStation,this);
		
		return true;
	}
	
	/**
	 * 显示添加闹铃的对话框;
	 */
	private void showAddAlarmDialog(final BusStation busStation,final Context ct){
		
		DialogTips dialog = new DialogTips(this,"睡眠计划","添加闹铃提醒？", "确定",true,true);
		
		dialog.SetOnSuccessListener(new DialogInterface.OnClickListener() {
			//接受确定响应;
			public void onClick(DialogInterface dialogInterface, int userId) {
				
				//创建闹钟;
				MrAlarm alarm=new MrAlarm();
				
				alarm.setPositionName(busStation.getTitle());
				alarm.setOn(true);
				alarm.setLatitude(busStation.getLocation().latitude);
				alarm.setLongtitude(busStation.getLocation().longitude);
				
				TransferData.getInstance().setData(alarm, DataType.ALARM);
				redictToActivity(ct, RemindActivity.class, null);
			}
		});
		
		dialog.show();
		dialog = null;
	}
}
