package com.remind.baidu;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.remind.app.CustomApplication;
import com.remind.config.Constant;
import com.remind.utils.LogUtils;

/**
 * 利用百度地图进行定位;
 * @author Render
 */
public class LocationHelper implements LocationListener{
	
	private short  mSpanTime=1000; //定位间隔;
	private LocationClient mLocationClient=null;
	private Location mCurrentLocation=null;
	
	/**
	 * 接受百度地图定位的回调类;
	 */
	private MyBDLocationListener mMyBDLocationListener=null;
	
	/**
	 * 自定义的定位回调接口,主要是对百度地图定位的封装;
	 */
	private OnLocationListener mLocationListener=null;
	
	/**
	 * Android中位置管理对象;
	 */
	private LocationManager locationManager=null; 
	private Context ct;
	
	public LocationHelper(Context ct){
		
		this.ct=ct;
		locationManager = (LocationManager)ct.getSystemService(Context.LOCATION_SERVICE); 
	}
	
	/**
	 * 设置自定义的监听器;
	 * @param locationListener
	 */
	public void setOnLocationListener(OnLocationListener locationListener){
		
		this.mLocationListener=locationListener;
	}

	/**
	 * 设置定位时间间隔;
	 * @param mSpanTime
	 */
	public void setmSpanTime(short mSpanTime) {
		this.mSpanTime = mSpanTime;
	}

	/**
	 * 停止定位;
	 */
	public void stop(){
		
		if(mLocationClient!=null){
			mLocationClient.stop();
			mLocationClient=null;
		}
	}
	
	/**
	 * 启动定位;
	 */
	public void start(){
		
		//1、启动百度的地图的定位;
		mLocationClient=new LocationClient(ct);
		mMyBDLocationListener=new MyBDLocationListener();
		mLocationClient.registerLocationListener(mMyBDLocationListener);
		LocationClientOption option = new LocationClientOption();
		option.setOpenGps(true);		//打开gps;
		option.setCoorType("bd09ll"); 	//设置坐标类型;
		option.setScanSpan(mSpanTime);	
		option.setAddrType("all");
		
		mLocationClient.setLocOption(option);
		mLocationClient.start();
	}
	
	/**
	 * 百度地图定位的接口;
	 * @author Render;
	 */
	public class MyBDLocationListener implements BDLocationListener{

		@Override
		public void onReceiveLocation(BDLocation location) {
			
			if(mLocationListener==null){
				return;
			}
			if(location.getCity()!=null){
				Constant.city=location.getCity();
				Constant.address=location.getAddrStr();
			}
			if(location!=null){
				
				if(mCurrentLocation!=null)
				{
					if(location.getLatitude()==mCurrentLocation.getLatitude()
					&&location.getLongitude()==mCurrentLocation.getLongitude())
					{
						return;
					}
				}
				
				mCurrentLocation=new Location(LocationManager.GPS_PROVIDER);
				mCurrentLocation.setLongitude(location.getLongitude());
				mCurrentLocation.setLatitude(location.getLatitude());
				
				CustomApplication.mLocation=mCurrentLocation;
				mLocationListener.onReceiveLocation(mCurrentLocation);	
			}
		}
	}
	
	public interface OnLocationListener {
		public void onReceiveLocation(Location location); 
	}

	@Override
	public void onLocationChanged(Location location) {
		
	  LogUtils.v("GPSTEST", "Got New Location of provider:"+location.getProvider());  
        if(mCurrentLocation!=null){  
            if(isBetterLocation(location, mCurrentLocation)){  
                LogUtils.v("GPSTEST", "It's a better location");  
                mCurrentLocation=location;  
            }  
            else{  
            	LogUtils.v("GPSTEST", "Not very good!");  
            }  
        } 
        else {
			mCurrentLocation=location;
		} 
        
		if(mLocationListener==null){
			return;
		}  
		if(location!=null){
			
			CustomApplication.mLocation=mCurrentLocation;
			mLocationListener.onReceiveLocation(mCurrentLocation);
		}
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		
	}

	@Override
	public void onProviderDisabled(String provider) {
		
	}

	private static final int CHECK_INTERVAL = 1000 * 30; 
	
	protected boolean isBetterLocation(Location location,  
            Location currentBestLocation) {  
        if (currentBestLocation == null) {  
            // A new location is always better than no location  
            return true;  
        }  
   
        // Check whether the new location fix is newer or older  
        long timeDelta = location.getTime() - currentBestLocation.getTime();  
        boolean isSignificantlyNewer = timeDelta > CHECK_INTERVAL;  
        boolean isSignificantlyOlder = timeDelta < -CHECK_INTERVAL;  
        boolean isNewer = timeDelta > 0;  
   
        // If it's been more than two minutes since the current location,  
        // use the new location  
        // because the user has likely moved  
        if (isSignificantlyNewer) {  
            return true;  
            // If the new location is more than two minutes older, it must  
            // be worse  
        } else if (isSignificantlyOlder) {  
            return false;  
        }  
   
        // Check whether the new location fix is more or less accurate  
        int accuracyDelta = (int) (location.getAccuracy() - currentBestLocation  
                .getAccuracy());  
        
        boolean isLessAccurate = accuracyDelta > 0;  
        boolean isMoreAccurate = accuracyDelta < 0;  
        boolean isSignificantlyLessAccurate = accuracyDelta > 200;  
   
        // Check if the old and new location are from the same provider  
        boolean isFromSameProvider = isSameProvider(location.getProvider(),  
                currentBestLocation.getProvider());  
   
        // Determine location quality using a combination of timeliness and  
        // accuracy  
        if (isMoreAccurate) {  
            return true;  
        } else if (isNewer && !isLessAccurate) {  
            return true;  
        } else if (isNewer && !isSignificantlyLessAccurate  
                && isFromSameProvider) {  
            return true;  
        }  
        return false;  
    }  
	
	/** Checks whether two providers are the same */  
    private boolean isSameProvider(String provider1, String provider2) {  
        if (provider1 == null) {  
            return provider2 == null;  
        }  
        return provider1.equals(provider2);  
    }  
}
