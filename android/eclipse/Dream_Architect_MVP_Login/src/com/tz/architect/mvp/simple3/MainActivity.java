package com.tz.architect.mvp.simple3;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.tz.architect.mvp.R;

public class MainActivity extends Activity implements MainView {

	private MainPresenter mainPresenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		this.mainPresenter = new MainPresenter();
		this.mainPresenter.attachView(this);
	}

	// 其实在Android当中，本身就是一个非常典型MVC架构
	// 在Android MVC中
	// M代表：数据
	// C代表: activity或者Fragment
	// V代表：视图
	// MVP适合大项目
	// MVP更加便于团队开发
	public void click(View v) {
		this.mainPresenter.login("Dream", "123456");
	}

	@Override
	public void onLoginResult(String result) {
		// 更新UI
		Toast.makeText(this, result, Toast.LENGTH_LONG).show();
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		this.mainPresenter.dettachView();
	}

}
