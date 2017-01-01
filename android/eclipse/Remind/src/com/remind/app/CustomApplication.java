package com.remind.app;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.model.LatLng;
import com.remind.utils.LogUtils;

import android.app.Application;
import android.location.Location;

/**
 * 自定义的Android的应用类,进行一些接口的初始化操作,如百度地图;
 * @author Render
 * 
 */
public class CustomApplication  extends Application{

	private static final String TAG="CustomApplication";
	
	private static CustomApplication mCustomApplication=null;
	
	/**
	 * 记录当前用户的位置;
	 */
	public static Location mLocation=null; 
	
	public static CustomApplication getInstance() {
		
		return mCustomApplication;
	}
	
	public double getLongtitude(){
		
		if(mLocation!=null)
		{
			return mLocation.getLongitude();
		}
		return 0.0;
	}
	
	public double getLatitude() {
		
		if(mLocation!=null)
		{
			return mLocation.getLatitude();
		}
		return 0.0;
	}
	
	/**
	 * 获取当前的位置;
	 * @return
	 */
	public LatLng getCurrentLatLng(){
		
		return new LatLng(getLatitude(), getLongtitude());
	}
	
	@Override
	public void onCreate() {
		
		super.onCreate();
		
		mCustomApplication=this;
		
		//初始化百度地图;
		SDKInitializer.initialize(this);
		
		LogUtils.i(TAG, "Baidu Map Init Completed!");
	}
}
