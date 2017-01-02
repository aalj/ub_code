package com.tz.architect.mvp.simple4;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.tz.architect.mvp.R;
import com.tz.architect.mvp.simple4.framework.impl.MainPresenter;
import com.tz.architect.mvp.simple4.framework.impl.MainView;
import com.tz.architect.mvp.simple4.framework.impl.MvpActivity;

public class MainActivity extends MvpActivity<MainView, MainPresenter> implements MainView {

	@Override
	public MainPresenter bindPresenter() {
		return new MainPresenter();
	}
	
	@Override
	public MainView bindView() {
		return this;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	// 其实在Android当中，本身就是一个非常典型MVC架构
	// 在Android MVC中
	// M代表：数据
	// C代表: activity或者Fragment
	// V代表：视图
	// MVP适合大项目
	// MVP更加便于团队开发
	public void click(View v) {
		getPresenter().login("Dream", "123456");
	}

	@Override
	public void onLoginResult(String result) {
		// 更新UI
		Toast.makeText(this, result, Toast.LENGTH_LONG).show();
	}
	
}
