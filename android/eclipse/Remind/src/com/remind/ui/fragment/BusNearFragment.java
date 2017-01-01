package com.remind.ui.fragment;

import java.util.List;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.mr.remind.R;
import com.remind.adapter.BusNearAdapter;
import com.remind.baidu.NearbySearchHelper;
import com.remind.baidu.NearbySearchHelper.OnNearbySearchListener;
import com.remind.bean.MrNearbyBus;
import com.remind.ui.BuslineDetailActivity;
import com.remind.ui.base.BaseFragment;
import com.remind.utils.LogUtils;
import com.remind.widget.HeaderLayout;
import com.remind.widget.XListView;
import com.remind.widget.HeaderLayout.HeaderStyle;
import com.remind.widget.XListView.IXListViewListener;

/**
 * 公交查询中的周边片段;
 * @author Render;
 */
public class BusNearFragment extends BaseFragment implements OnNearbySearchListener,IXListViewListener
	,OnItemClickListener{

	public final String TAG="BusNearActivity";
	
	/**
	 * 周边搜索帮助类;
	 */
	public NearbySearchHelper nearbySearchHelper=null;
	private XListView  mBusNearListView;
	private MyHandler mHandler;    //处理消息的Handle对象;
	
	private View mView;
	private Context mContext;
	private HeaderLayout mHeaderLayout;
	
	private boolean bFirstExecute=true;	//第一次执行;
	
	/**
	 * 适配器;
	 */
	private BusNearAdapter busNearAdapter=null;
	
	@Override
	public void execute() {
		
		if(bFirstExecute){
			initData();
			bFirstExecute=false;
		}
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		mContext=getActivity();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		mView = inflater.inflate(R.layout.fragment_bus_near, container, false); 
		mContext=getActivity();
		
		initView();
		initData();
		
		return mView;
	}
	
	/**
	 * 初始化头部;
	 */
	private void initView()
	{
		mHeaderLayout=(HeaderLayout)mView.findViewById(R.id.common_actionbar);
		mHeaderLayout.init(HeaderStyle.DEFAULT_TITLE);
		mHeaderLayout.setDefaultTitle(getString(R.string.main_tab_bus_near));
		
		//初始化ListView;
		mBusNearListView=(XListView)mView.findViewById(R.id.list_near_bus_info);
		mBusNearListView.setPullLoadEnable(false);
		mBusNearListView.setPullRefreshEnable(true);
		mBusNearListView.setXListViewListener(this);
		mBusNearListView.setOnItemClickListener(this);
		mBusNearListView.pullRefreshing();
		
		//去掉中间的横线;
		mBusNearListView.setDividerHeight(0);
	}
	
	/**
	 * 初始化数据;
	 */
	private void initData(){
	
		/**
		 * 创建周边搜索帮助类;
		 */
		nearbySearchHelper=new NearbySearchHelper();
		nearbySearchHelper.init();
		nearbySearchHelper.setNearBySearchListener(this);
		
		mHandler=new MyHandler();
		mHandler.sendEmptyMessage(0);
	}

	@Override
	public void onDestroy() {
		
		/**
		 * 销毁周边搜索帮助类;
		 */
		nearbySearchHelper.Destory();
		super.onDestroy();
	}

	/**
	 * 搜索周边公交,步骤：先定位,再搜索;
	 */
	private void excuteSearch(){
				
		//开始搜索周边信息;
		nearbySearchHelper.searchNearby();
	}
	
	@Override
	public void onRefresh() {
	
		if(null==mHandler){
			return;
		}
		mHandler.sendEmptyMessage(0);
	}

	@Override
	public void onLoadMore() {
		
	}

	/**
	 * 点击Item节点，进行跳转;
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		
		MrNearbyBus nearbyBus = (MrNearbyBus) busNearAdapter.getItem(position-1);
		
		if(nearbyBus==null){
			return;
		}
		
		Bundle bundle=new Bundle();
		bundle.putString("buslineUid", nearbyBus.getUid());
		bundle.putString("busName", nearbyBus.getBusName());
		bundle.putString("reverseBuslineUid",nearbyBus.getReverseUid());
		
		redictToActivity(mContext, BuslineDetailActivity.class, bundle);
	}
	
	private void refreshPull(){
		if (mBusNearListView.getPullRefreshing()) {
			mBusNearListView.stopRefresh();
		}
	}

	@Override
	public void onRefreshBusNearby(List<MrNearbyBus> list) {
			
		for(int i=0;i<list.size();i++){
			
			LogUtils.i(TAG, list.get(i).getBusName());	
		}
		
		if(busNearAdapter==null){
			busNearAdapter=new BusNearAdapter(mContext, list);
			mBusNearListView.setAdapter(busNearAdapter);
		}
		else {
			busNearAdapter.clear();
			busNearAdapter.appendToList(list);
		}
		
		refreshPull();
	}
	

	/**
	 * 自定义的Handle对象;
	 * @author Render;
	 *
	 */
	private class MyHandler extends Handler{
		
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			
			switch(msg.what){
				case 0:{
					excuteSearch();
					break;
				}
			}
		}
	}
}
