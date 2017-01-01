package com.remind.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mr.remind.R;
import com.remind.ui.base.BaseFragment;

/**
 * 设置片段;
 * @author Render
 */
public class SettingFragment extends BaseFragment{

	private View mView;
	
	@Override
	public void execute() {

	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		mContext=getActivity();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,  
            Bundle savedInstanceState){
		mView = inflater.inflate(R.layout.fragment_setting, container, false); 
		
		
		return mView;
	}
}
