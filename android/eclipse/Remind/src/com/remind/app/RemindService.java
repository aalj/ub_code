package com.remind.app;

import java.util.List;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.DistanceUtil;
import com.mr.remind.R;
import com.remind.baidu.LocationHelper;
import com.remind.baidu.LocationHelper.OnLocationListener;
import com.remind.bean.MrAlarm;
import com.remind.config.Constant;
import com.remind.ui.AlarmRingActivity;
import com.remind.utils.LogUtils;

import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.os.Vibrator;

public class RemindService extends Service implements OnLocationListener{

	private static final String TAG = "RemindService";
	
	private LocationHelper mLocationHelper=null;
	private List<MrAlarm> mAlarms=null;
	private MediaPlayer mediaPlayer=null;
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		
		mLocationHelper=new LocationHelper(this);
		mLocationHelper.setOnLocationListener(this);
		mLocationHelper.start();
		
		mediaPlayer = MediaPlayer.create(this, R.raw.notify);  
	    mediaPlayer.setLooping(true);
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		
        LogUtils.v(TAG, "onDestroy");  
        if (mediaPlayer != null) {  
            mediaPlayer.stop();  
            mediaPlayer.release();  
        }  
        mLocationHelper.stop();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		
		mAlarms=(List<MrAlarm>)(intent.getExtras().getSerializable(Constant.ALARMS));
		
		JudgeAlarm();
	}

	@Override
	public void onReceiveLocation(Location location) {
		
		Intent intent=new Intent();
		intent.setAction(Constant.BROADCAST_ACTION);
		intent.putExtra(Constant.BROADCAST_VALUE_TYPE, Constant.TYPE_LOCATION_COMPLETED);
		
		sendBroadcast(intent);
		
		JudgeAlarm();
	}
	
	/**
	 * 判断闹铃是否响起;
	 */
	private void JudgeAlarm(){
		
		double longtitude=0.0;
		double latitude=0.0;
		boolean isCloseAlarm=true;
		
		int size=mAlarms.size();
		int [] ids=new int[size];
		int index=0;
		
		for(MrAlarm alarm:mAlarms){
			
			if(alarm.isOn())
			{
				longtitude=alarm.getLongtitude();
				latitude=alarm.getLatitude();
				LatLng currentLatLng=CustomApplication.getInstance().getCurrentLatLng();
				LatLng alarmLatLng=new LatLng(latitude, longtitude);
				
				double distance=DistanceUtil.getDistance(currentLatLng, alarmLatLng);
				
				if(distance<=Constant.alarmDistanceThreshold){
					isCloseAlarm=false;
					start();
					ids[index++]=Integer.parseInt(alarm.getId());
				}
			}
		}
		
		if(isCloseAlarm){
			stop();
		}
		else { //发送广播提醒;
			
			Intent intent=new Intent();
			intent.setAction(Constant.BROADCAST_ACTION);
			intent.putExtra(Constant.BROADCAST_VALUE_TYPE, Constant.TYPE_ALARM_RING);
			intent.putExtra(Constant.IDS, ids);
			
			sendBroadcast(intent);
			
			//启动闹铃响起的Activity;
			if(Constant.bAlarmRingActivity==false){
			    
				Intent intentv = new Intent();  
			    intentv.setClass(this, AlarmRingActivity.class);
	            intentv.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	            
	            //传入当前所有的闹钟信息;
	            intentv.putExtra(Constant.IDS, ids);
	            startActivity(intentv);  
			}
		}
	}
	
	/**
	 * 启动MediaPlayer;
	 */
	private void start(){
		
		 if (!mediaPlayer.isPlaying()) {
			 mediaPlayer.start();
			 
			 long pattern[]={0,1000,500};
			 Vibrator vib = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
		     vib.vibrate(pattern, 0);
	    }  
	}
	
	/**
	 * 停止mediaPlayer;
	 */
	private void stop(){
		
		if(mediaPlayer.isPlaying()){
			mediaPlayer.pause();
			mediaPlayer.seekTo(0);
			Vibrator vib = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
			vib.cancel();
		}	
	}
}
