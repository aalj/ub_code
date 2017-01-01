package com.remind.ui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.mr.remind.R;
import com.remind.app.RemindService;
import com.remind.bean.MrAlarm;
import com.remind.bean.RemindManager;
import com.remind.config.Constant;
import com.remind.db.AlarmColumn;
import com.remind.db.DBHelper;
import com.remind.ui.base.BaseActivity;
import com.remind.widget.Slidebutton;
import com.remind.widget.Slidebutton.OnToggleStateChangedListener;

public class AlarmRingActivity extends BaseActivity implements OnToggleStateChangedListener{

	/**
	 * 当前的闹钟链表;
	 */
	private List<MrAlarm> mAlarms=null;
	
	/**
	 * 正在响的闹钟列表;
	 */
	private List<MrAlarm> mRingAlarms=new ArrayList<MrAlarm>();
	
	private Slidebutton mSlidebutton;
	private TextView mTvPostionName;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alarm_ring);
		
		initView();
		initData();
	}

	private void initView(){
		
		initTopBarForOnlyTitle("到达目的地");
		
		mTvPostionName=(TextView) findViewById(R.id.tv_position_name);
		mSlidebutton=(Slidebutton) findViewById(R.id.slideButton);
		
		mSlidebutton.setChangedListener(this);
		mSlidebutton.setToggleState(false);
	}

	private void initData(){
		
		//标志着已经启动;
		Constant.bAlarmRingActivity=true;
		
		Intent intent=getIntent();
		
		//接受传入的闹钟列表和正在响的闹钟id;
		int []ids=intent.getExtras().getIntArray(Constant.IDS);
		mAlarms=RemindManager.getInstance().getAllAlarms();
		
		List<String> positionNames=new ArrayList<String>();
		String strName=null;
		
		for(int i=0;i<ids.length;i++){
			
			int id=ids[i];
			for(MrAlarm alarm:mAlarms){
				
				if(Integer.parseInt(alarm.getId())==id){
					
					strName=alarm.getPositionName();
					
					//添加到正在响的闹铃列表中;
					mRingAlarms.add(alarm);
					
					if(positionNames.contains(strName)==false){
						positionNames.add(strName);
					}
				}
			}
		}
		strName="";
		for(int i=0;i<positionNames.size();++i)
		{
			strName=strName+positionNames.get(i)+" ";
		}
		
		strName="目的地："+strName;
		mTvPostionName.setText(strName);
	}
	
	@Override
	public void onToggleStateChanged(boolean state) {
		
		closeAlarm();
		
		finish();
	}
	
	/**
	 * 关闭闹铃;
	 */
	private void closeAlarm(){
		
		DBHelper dbHelper=DBHelper.getInstance(this);
		
		//先更新数据库,关闭闹钟;
		for(MrAlarm alarm:mRingAlarms){
			
			ContentValues cV=new ContentValues();
			cV.put(AlarmColumn.ISON,false);
			cV.put(AlarmColumn.LATITUDE, alarm.getLatitude());
			cV.put(AlarmColumn.LONGTITUDE, alarm.getLongtitude());
			
			String []args={String.valueOf(alarm.getId())};
			
			dbHelper.update(AlarmColumn.TABLE_NAME,cV, "_id=?", args);
			alarm.setOn(false);
		}
		
		//把参数传到服务中;
		Intent intent=new Intent(this,RemindService.class);
		intent.putExtra(Constant.ALARMS, (Serializable)mAlarms);
		startService(intent);
	}
	
	@Override
	protected void onDestroy() {
		Constant.bAlarmRingActivity=false;
		super.onDestroy();
	}
	
	//禁用回退键;
	@Override
	public void onBackPressed() {
		return;
	}
}
