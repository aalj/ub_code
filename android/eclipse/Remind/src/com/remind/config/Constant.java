package com.remind.config;

public final class Constant {

	public static final String NETWORK_TYPE_WIFI = "wifi";
	public static final String NETWORK_TYPE_MOBILE = "mobile";
	public static final String NETWORK_TYPE_ERROR = "error";
	
	public static final String PRE_NAME = "my_pre";
	public static final int nearbyRadius=1000;  //百度地图搜索周边环境的范围;  
	
	public static String city="";
	public static String address="";

	public static final int	alarmDistanceThreshold=600; //闹铃响起的距离阈值;
		
	/**
	 * 闹铃服务发送的广播有关的值;
	 */
	public static final int TYPE_LOCATION_COMPLETED=1;	//定位完成;
	public static final int TYPE_ALARM_RING=2;			//闹铃响起;
	public static final String BROADCAST_VALUE_TYPE="broadcast_value_type";	//广播值类型;
	public static final String BROADCAST_ACTION="com.remind.app.BroadcastReceiver";
	public static final String IDS="ids";			//传递正在响的闹铃;
	public static final String ALARMS="alarms";		//闹钟列表;
	
	public static boolean bAlarmRingActivity=false; //判断当前的闹铃提醒的Activity是否激活状态;
	
}
