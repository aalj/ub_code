package com.tz.architect.mvp.simple5;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.tz.architect.mvp.simple5.framework.view.impl.MvpActivity;
import com.tz.architect.mvp.simple5.login.MainPresenter;
import com.tz.architect.mvp.simple5.login.MainView;
import com.wdsunday.R;

public class MainActivity extends MvpActivity<MainView, MainPresenter> implements MainView{
	
	@Override
	public MainPresenter createPresenter() {
		return new MainPresenter(this);
	}
	
	@Override
	public MainView createView() {
		return this;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void click(View v) {
		getPresenter().login("Dream", "123456");
	}

	@Override
	public void onLoginResult(String result) {
		// 更新UI
		Toast.makeText(this, result, Toast.LENGTH_LONG).show();
	}
}
