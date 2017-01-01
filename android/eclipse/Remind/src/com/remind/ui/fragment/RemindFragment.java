package com.remind.ui.fragment;

import java.io.Serializable;
import java.util.List;

import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

import com.mr.remind.R;
import com.remind.adapter.AlarmAdapter;
import com.remind.adapter.AlarmAdapter.OnAlarmStateChangeListener;
import com.remind.app.RemindService;
import com.remind.bean.RemindManager;
import com.remind.bean.MrAlarm;
import com.remind.bean.TransferData;
import com.remind.bean.TransferData.DataType;
import com.remind.config.Constant;
import com.remind.db.AlarmColumn;
import com.remind.db.DBHelper;
import com.remind.ui.base.BaseFragment;
import com.remind.view.dialog.DialogTips;
import com.remind.widget.HeaderLayout;
import com.remind.widget.HeaderLayout.HeaderStyle;

/**
 * 启动睡眠计划的Activity,该Activity是由公交线路详细信息Activity启动的，里面直接采用睡眠计划片段;
 * @author Render;
 */
public class RemindFragment extends BaseFragment implements OnItemClickListener,
	OnItemLongClickListener,OnAlarmStateChangeListener{

	private ListView mListView;
	private DBHelper mDBHelper;			//数据库操作帮助类;
	private AlarmAdapter mAlarmAdapter;	
	private List<MrAlarm> mAlarms; 		// 当前所有的闹铃列表;
	private MyHandler mHandler;    		//处理消息的Handle对象;
	private BroadcastReceiver mReceiver=null;	//广播对象;
	
	private View mView;					//绑定的View;
	private Context mContext;			//上下文Context;
	private HeaderLayout mHeaderLayout;
	
	/**
	 * 标志handle中的消息类型;
	 */
	private final static int MSG_UPDATE_ALARMS=101;	//刷新闹铃列表;
	private final static int MSG_READ_ALARMS=100;	//读取闹铃列表;
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
	
		initData();
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		mContext=getActivity();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,  
            Bundle savedInstanceState){
		mView = inflater.inflate(R.layout.fragment_remind, container, false); 
		
		initView();
		
		initData();
		
		return mView;
	}
	
	private void initView(){
		
		mHeaderLayout=(HeaderLayout)mView.findViewById(R.id.common_actionbar);
		mHeaderLayout.init(HeaderStyle.DEFAULT_TITLE);
		mHeaderLayout.setDefaultTitle(getString(R.string.main_tab_remind));
		
		mListView=(ListView)mView.findViewById(R.id.lv_sleep_plan_detail);
	}
	
	//初始化;
	private void initData(){
		
		mDBHelper=DBHelper.getInstance(mContext);
		mAlarmAdapter=new AlarmAdapter(null, mContext);
		mAlarmAdapter.setOnAlarmStateChangeLister(this);
		mListView.setAdapter(mAlarmAdapter);
		mListView.setOnItemClickListener(this);
		mListView.setOnItemLongClickListener(this);
		
		mHandler=new MyHandler();
		mHandler.sendEmptyMessage(MSG_READ_ALARMS);
		
		registerBroadcastReceiver();
	}

	/**
	 * 插入添加的闹钟到当前的列表中;
	 */
	private void initAlarms(){
		
		//插入添加的闹铃;
		Object data=TransferData.getInstance().getData(DataType.ALARM);
		
		//取完数据后把数据置空;
		TransferData.getInstance().clearData();
		
		if(data!=null){

			MrAlarm alarm=(MrAlarm) data;
			
			ContentValues cv=new ContentValues();
			cv.put(AlarmColumn.POSITIONNAME, alarm.getPositionName());
			cv.put(AlarmColumn.LONGTITUDE, alarm.getLongtitude());
			cv.put(AlarmColumn.LATITUDE, alarm.getLatitude());
			cv.put(AlarmColumn.ISON, alarm.isOn());
			
			//向数据库中插入新添加的闹铃数据;
			mDBHelper.insert(AlarmColumn.TABLE_NAME, cv);
		}		
		//读取所有的闹铃信息并更新;
		mAlarms=RemindManager.getInstance().getAllAlarms();
		
		updateAlarms();
	}
	
	/**
	 * 更新界面上的闹钟列表;
	 */
	public void updateAlarms(){
		
		if(mAlarms!=null){
			mAlarmAdapter.updateListView(mAlarms);
		}
	}
	
	/**
	 * 启动服务;
	 */
	private void startAlarmService(){
		if(mAlarms.size()==0){
			return;
		}
		
		Intent intent=new Intent(mContext,RemindService.class);
		intent.putExtra(Constant.ALARMS, (Serializable)mAlarms);
		mContext.startService(intent);
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view,
			int position, long id) {
		
		MrAlarm alarm=(MrAlarm) mAlarmAdapter.getItem(position);
		showDeleteDialog(alarm);
		
		return false;
	}
	
	/**
	 * 删除闹铃;
	 * @param alarm
	 */
	private void showDeleteDialog(final MrAlarm alarm){
		
		if(null==alarm){
			return;
		}
		
		DialogTips dialog = new DialogTips(mContext,"温馨提示","删除该闹铃？", "确定",true,true);
		dialog.SetOnSuccessListener(new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialogInterface, int userId) {
				
				mDBHelper.delete(AlarmColumn.TABLE_NAME, Integer.parseInt(alarm.getId()));
				
				if(mAlarms.contains(alarm)){
					mAlarms.remove(alarm);
				}
				
				//清空后,需要更新服务中的闹钟链表对象;
				updateAlarms();
				startAlarmService();
			}
		});
		dialog.show();
		dialog = null;
	}
	
	@Override
	public void onAlarmStateChange(boolean b, MrAlarm alarm) {
		
		if(false==mAlarms.contains(alarm)){
			return;
		}
		
		ContentValues cV=new ContentValues();
		cV.put(AlarmColumn.ISON,b);
		cV.put(AlarmColumn.LATITUDE, alarm.getLatitude());
		cV.put(AlarmColumn.LONGTITUDE, alarm.getLongtitude());
		
		String []args={String.valueOf(alarm.getId())};
		
		mDBHelper.update(AlarmColumn.TABLE_NAME,cV, "_id=?", args);
		alarm.setOn(b);
		
		//如果是关闭闹铃,则需要判断闹铃是否正在响起;
		if(b==false){
			if(alarm.isRing()){	
				alarm.setRing(false);
			}
		}
		mHandler.sendEmptyMessage(MSG_UPDATE_ALARMS);
	}
	
	/**
	 * 注册广播;
	 */
	private void registerBroadcastReceiver(){
	
		mReceiver=new BroadcastReceiver(){

			@Override
			public void onReceive(Context context, Intent intent) {
				
				int value=intent.getExtras().getInt(Constant.BROADCAST_VALUE_TYPE);
				
				if(value==Constant.TYPE_LOCATION_COMPLETED){
					updateAlarms();				
				}
				else if(value==Constant.TYPE_ALARM_RING){
					
					int []ids=intent.getExtras().getIntArray(Constant.IDS);
					for(int i=0;i<ids.length;i++){
					
						int id=ids[i];
						for(MrAlarm alarm:mAlarms){
							
							if(Integer.parseInt(alarm.getId())==id){
								alarm.setRing(true);
							}
						}
					}
					updateAlarms();
				}
			}
		};
		
		IntentFilter filter=new IntentFilter();
		filter.addAction(Constant.BROADCAST_ACTION);
		mContext.registerReceiver(mReceiver,filter);
	}
	
	@Override
	public void onDestroy() {
		
		super.onDestroy();
		
		mContext.unregisterReceiver(mReceiver);
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
				case MSG_UPDATE_ALARMS:
					updateAlarms();
					startAlarmService();
					break;
				case MSG_READ_ALARMS:
					initAlarms();
					startAlarmService();
					break;
			}
		}
	}
}
