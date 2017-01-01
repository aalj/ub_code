package com.remind.ui.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.remind.config.Constant;
import com.remind.utils.Sputil;

public abstract class BaseFragment extends Fragment {

	protected Context mContext;
	protected Sputil sputil;
	
	/**
	 * 执行数据更新的函数;
	 */
	public abstract void execute();
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = getActivity();
		if(null == sputil){
			sputil = new Sputil(mContext, Constant.PRE_NAME);
		}
	}
	
	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	public void onPause() {
		super.onPause();
	}
	
	/**
	 * Activity跳转
	 * @param context
	 * @param targetActivity
	 * @param bundle
	 */
	public void redictToActivity(Context context,Class<?> targetActivity,Bundle bundle){
		Intent intent = new Intent(context, targetActivity);
		if(null != bundle){
			intent.putExtras(bundle);
		}
		startActivity(intent);
	}	
}
