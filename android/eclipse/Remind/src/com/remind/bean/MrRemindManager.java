package com.remind.bean;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;

import com.remind.app.CustomApplication;
import com.remind.db.AlarmColumn;
import com.remind.db.DBHelper;

/**
 * 闹钟对象管理;
 * @author Render;
 *
 */
public class RemindManager {

	private static RemindManager alarmManager=null;
	
	/**
	 * 当前所有的闹钟列表;
	 */
	private static List<MrAlarm> mAlarms;
	
	private RemindManager(){
		
	}
	
	public static RemindManager getInstance(){
		
		if(null==alarmManager){
			
			alarmManager=new RemindManager();
			mAlarms=new ArrayList<MrAlarm>();
			
		}
		
		return alarmManager;
	}
	
	/**
	 * 获取所有的闹钟列表;
	 * @return
	 */
	public List<MrAlarm> getAllAlarms(){
		
		mAlarms.clear();
		
		readAllAlarms();
		
		return mAlarms;
	}
	
	/**
	 * 创建新的闹钟;
	 * @return
	 */
	public MrAlarm newAlarm(){
		
		MrAlarm alarm=new MrAlarm();
		
		mAlarms.add(alarm);
		return alarm;
	}
	
	/**
	 * 读取所有的闹钟列表;
	 */
	private static void readAllAlarms(){
		
		Context ct=CustomApplication.getInstance().getApplicationContext();
		DBHelper dbHelper=DBHelper.getInstance(ct);
		
		Cursor cursor=dbHelper.query(AlarmColumn.TABLE_NAME, null, null, null);
		
		int nIndex=0;
	    while(cursor.moveToNext())  
	    {  
	    	MrAlarm alarm=new MrAlarm();
	    	nIndex=cursor.getColumnIndex(AlarmColumn._ID);
	    	String id=cursor.getString(nIndex);
	    	alarm.setId(id);
	    	
	    	nIndex=cursor.getColumnIndex(AlarmColumn.POSITIONNAME);
	    	String positionName=cursor.getString(nIndex);
	    	alarm.setPositionName(positionName);
	    	
	    	nIndex=cursor.getColumnIndex(AlarmColumn.LONGTITUDE);
	    	double longtitude=cursor.getDouble(nIndex);
	    	alarm.setLongtitude(longtitude);
	    	
	    	nIndex=cursor.getColumnIndex(AlarmColumn.LATITUDE);
	    	double latitude=cursor.getDouble(nIndex);
	    	alarm.setLatitude(latitude);
	    	
	    	nIndex=cursor.getColumnIndex(AlarmColumn.ISON);
	    	int ison=cursor.getInt(nIndex);
	    	if(ison!=0){
	    		alarm.setOn(true);
	    	}else {
				alarm.setOn(false);
			}
	    	
	    	mAlarms.add(alarm);
	    }
	}
}
