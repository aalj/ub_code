package com.remind.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.mr.remind.R;
import com.remind.db.DBHelper;
import com.remind.ui.base.BaseFragment;
import com.remind.ui.base.BaseFragmentActivity;
import com.remind.ui.fragment.BusNearFragment;
import com.remind.ui.fragment.MainFragment;
import com.remind.ui.fragment.RemindFragment;
import com.remind.ui.fragment.SettingFragment;
import com.remind.utils.NetWorksUtils;
import com.remind.utils.ToastFactory;

public class MainActivity extends BaseFragmentActivity{

	public static final String TAG="MainAcivity";
	
	private Button [] mTabButtons;
	private BaseFragment[] mFragments;
	
	private MainFragment mainFragment;
	private BusNearFragment busNearFragment;
	private RemindFragment remindFragment;
	private SettingFragment settingFragment;
	
	private int  index;
	private int currentTabIndex;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if(!NetWorksUtils.isNetworkAvailable(this))
		{
			Toast toast=ToastFactory.getToast(this, getString(R.string.network_errors));
			toast.show();
		}
		
		initView();
		initTab();
	}
	
	/**
	 * 初始化主界面;
	 */
	private void initView(){
		mTabButtons = new Button[4];
		mTabButtons[0] = (Button) findViewById(R.id.btn_main_index);
		mTabButtons[1] = (Button) findViewById(R.id.btn_bus_near);
		mTabButtons[2] = (Button) findViewById(R.id.btn_remind);
		mTabButtons[3] = (Button) findViewById(R.id.btn_setting);

		//把第一个tab设为选中状态
		mTabButtons[0].setSelected(true);
	}
	
	/**
	 * 初始化Tab页;
	 */
	private void initTab(){
		mainFragment = new MainFragment();
		busNearFragment = new BusNearFragment();
		remindFragment = new RemindFragment();
		settingFragment=new SettingFragment();
		
		mFragments = new BaseFragment[] {mainFragment, busNearFragment, remindFragment,settingFragment };
		
		// 添加显示第一个fragment
		FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
		
		fragmentTransaction.add(R.id.main_container, mainFragment)
		.add(R.id.main_container, busNearFragment).add(R.id.main_container, remindFragment)
		.add(R.id.main_container, settingFragment);
		
		fragmentTransaction.show(mainFragment).hide(busNearFragment).hide(remindFragment).hide(settingFragment);
		fragmentTransaction.commit();
	}
	
	/**
	 * 切换Tab;
	 * @param view
	 */
	public void onTabSelect(View view) {
		
		switch (view.getId()) {
		case R.id.btn_main_index:
			index = 0;
			break;
		case R.id.btn_bus_near:
			index = 1;
			break;
		case R.id.btn_remind:
			index = 2;
			break;
		case R.id.btn_setting:
			index=3;
			break;
		}
		if (currentTabIndex != index) {
			FragmentTransaction trx = getSupportFragmentManager().beginTransaction();
			trx.hide(mFragments[currentTabIndex]);
			if (!mFragments[index].isAdded()) 
			{
				trx.add(R.id.main_container, mFragments[index]);
			}
			trx.show(mFragments[index]).commit();
			mFragments[index].execute();
		}
		mTabButtons[currentTabIndex].setSelected(false);
		//把当前tab设为选中状态
		mTabButtons[index].setSelected(true);
		currentTabIndex = index;
	}

	private static long firstTime;
	
	/**
	 * 连续按两次返回键就退出
	 */
	@Override
	public void onBackPressed() {
		
		if (firstTime + 2000 > System.currentTimeMillis()) {
			super.onBackPressed();
		} else {
			ToastFactory.getToast(this, "再按一次退出程序").show();
		}
		firstTime = System.currentTimeMillis();
	}
	
	@Override
	protected void onDestroy() 
	{
		super.onDestroy();
		
		//关闭数据库;
        try {
            DBHelper db = DBHelper.getInstance(this);
            db.closeDb();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}
