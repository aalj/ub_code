package com.remind.ui.fragment;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ZoomControls;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationConfiguration.LocationMode;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.mr.remind.R;
import com.remind.baidu.LocationHelper;
import com.remind.baidu.LocationHelper.OnLocationListener;
import com.remind.ui.SearchPositionActivity;
import com.remind.ui.base.BaseFragment;
import com.remind.utils.ToastFactory;
import com.remind.widget.ClearEditText;

public class MainFragment extends BaseFragment implements OnLocationListener{

	private MapView  mMapView=null;
	private BaiduMap mBaiduMap = null;
	private ClearEditText mEditView; 
	private ImageView mLeftImageView;
	private ImageView mRightImageView;
	private LocationHelper mLocationHelper;
	private LocationMode mCurrentMode;
	private Button mRequestLocButton;
	private BitmapDescriptor mCurrentMarker=null;
	private boolean mIsFirstLoc=true;
	private Button mZoomInButton;
	private Button mZoomOutButton;
	
	private View mView;
	private Context mContext;
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
	
		initData();
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
		
		mView=inflater.inflate(R.layout.fragment_main, container,false);
		
		initView();
		initData();
		return mView;
	}

	/**
	 * 初始化界面;
	 */
	private void initView(){
	
		mMapView=(MapView)mView.findViewById(R.id.bmapView);
		
		//隐藏放大缩小控件;
		int childCount = mMapView.getChildCount();
        View zoom = null;
        for (int i = 0; i < childCount; i++) 
        {
            View child = mMapView.getChildAt(i);
            if (child instanceof ZoomControls) 
            {
                zoom = child;
                break;
            }
        }
        zoom.setVisibility(View.GONE);

		mEditView=(ClearEditText)mView.findViewById(R.id.et_search);		
		mLeftImageView=(ImageView)mView.findViewById(R.id.iv_left);
		mRightImageView=(ImageView)mView.findViewById(R.id.iv_right);
		mRequestLocButton=(Button)mView.findViewById(R.id.btn_map_mode);
		mZoomInButton=(Button)mView.findViewById(R.id.btn_zoom_in);
		mZoomOutButton=(Button)mView.findViewById(R.id.btn_zoom_out);
		
		mRightImageView.setVisibility(View.GONE);
		mLeftImageView.setVisibility(View.GONE);
	}
	
	
	private void initData(){
		mCurrentMode = LocationMode.NORMAL;
		mBaiduMap=mMapView.getMap();
	
		OnClickListener btnClickListener = new OnClickListener(){
	
			@Override
			public void onClick(View v) {
				if(v.equals(mRequestLocButton)){
					performMode();
				}else if(v.equals(mZoomInButton)){
					performZoomIn();
				}else if(v.equals(mZoomOutButton)){
					performZoomOut();
				}else if(v.equals(mEditView)){
					performSearchPosition();
				}
			}
		};
		
		mRequestLocButton.setOnClickListener(btnClickListener);
		mZoomInButton.setOnClickListener(btnClickListener);
		mZoomOutButton.setOnClickListener(btnClickListener);
		mLeftImageView.setOnClickListener(btnClickListener);
		mEditView.setOnClickListener(btnClickListener);
		
		// 开启定位图层,定位初始化;
		mBaiduMap.setMyLocationEnabled(true);
	    mBaiduMap.setBuildingsEnabled(true);
	    mBaiduMap.setTrafficEnabled(true);
	    
	    //开始定位;
	    mLocationHelper=new LocationHelper(mContext);
	    mLocationHelper.setOnLocationListener(this);
	    mLocationHelper.start();
	}
	
	@Override
	public void onReceiveLocation(Location location) {
		if (location == null || mMapView == null)
			return;
		
		MyLocationData locData = new MyLocationData.Builder()
				.accuracy(50).direction(100).latitude(location.getLatitude())
				.longitude(location.getLongitude()).build();
		
		mBaiduMap.setMyLocationData(locData);
		
		if (mIsFirstLoc) 
		{
			mIsFirstLoc = false;
			LatLng ll = new LatLng(location.getLatitude(),location.getLongitude());
			MapStatusUpdate u1 = MapStatusUpdateFactory.newLatLng(ll);
			mBaiduMap.setMapStatus(u1);
			MapStatusUpdate u2 = MapStatusUpdateFactory.zoomTo(16.0f);
			mBaiduMap.setMapStatus(u2);
		}
	}
	
	/**
	 * 处理显示模式;
	 */
	private void performMode(){
		
		switch (mCurrentMode) {
		case NORMAL:
			
			mRequestLocButton.setBackgroundResource(R.drawable.main_icon_follow);
			mCurrentMode = LocationMode.FOLLOWING;
			mBaiduMap.setMyLocationConfigeration(new MyLocationConfiguration(
							mCurrentMode, true, mCurrentMarker));
			break;
		case FOLLOWING:
			
			mRequestLocButton.setBackgroundResource(R.drawable.main_icon_compass);
			mCurrentMode = LocationMode.COMPASS;
			mBaiduMap.setMyLocationConfigeration(new MyLocationConfiguration(
							mCurrentMode, true, mCurrentMarker));
			break;
		case COMPASS:
			
			mRequestLocButton.setBackgroundResource(R.drawable.main_icon_location);
			mCurrentMode = LocationMode.NORMAL;
			mBaiduMap.setMyLocationConfigeration(new MyLocationConfiguration(
							mCurrentMode, true, mCurrentMarker));
			break;
		}
	}
	
	/**
	 * 处理放大;
	 */
	private void performZoomIn(){
		
		float zoomMaxLevel=mBaiduMap.getMaxZoomLevel();
		float zoomLevel=mBaiduMap.getMapStatus().zoom;
		
		zoomLevel++;
		if(zoomLevel>zoomMaxLevel)
		{
			zoomLevel=zoomMaxLevel;
			
			Toast toast=ToastFactory.getToast(mContext, "已放大至最高级别");
			toast.show();
		}
		
		MapStatusUpdate u = MapStatusUpdateFactory.zoomTo(zoomLevel);
		mBaiduMap.animateMapStatus(u);
	}
	
	/**
	 * 处理缩小;
	 */
	private void performZoomOut(){
		float zoomMinLevel=mBaiduMap.getMinZoomLevel();
		float zoomLevel=mBaiduMap.getMapStatus().zoom;
		
		zoomLevel--;
		if(zoomLevel<zoomMinLevel)
		{
			zoomLevel=zoomMinLevel;
			
			Toast toast=ToastFactory.getToast(mContext, "已缩小至最低级别");
			toast.show();
		}
		MapStatusUpdate u = MapStatusUpdateFactory.zoomTo(zoomLevel);
		mBaiduMap.animateMapStatus(u);
	}
	
	/**
	 * 处理位置地点搜索;
	 */
	private void performSearchPosition(){
		redictToActivity(mContext, SearchPositionActivity.class, null);
	}
	
	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		mMapView.onPause();
	}
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		mMapView.onResume();
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		
		//清空数据;
		mBaiduMap.setMyLocationEnabled(false);
		mLocationHelper.stop();
		mMapView.onDestroy();
		mMapView = null;
	}
}
