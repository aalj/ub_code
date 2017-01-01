package com.remind.adapter;

import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

public class BasePageAdapter extends FragmentStatePagerAdapter {

	public ArrayList<Fragment> mFragments = new ArrayList<Fragment>();
	public ArrayList<String> tabs=new ArrayList<String>();
	
	private Activity mActivity;

	public BasePageAdapter(FragmentActivity activity) {
		super(activity.getSupportFragmentManager());
		this.mActivity = activity;
	}

	public void addFragment(List<String> strNames) {

		tabs.addAll(strNames);

	/*
		for(int i=0;i<strNames.size();i++){
			if (strNames.get(i).equals(Constant.ROUTE)) {
				addTab(new RouteFragment());
			} else if (strNames.get(i).equals(Constant.STATION)) {
				addTab(new PositionFragment());
			} else if (strNames.get(i).equals(Constant.TRANSFER)) {
				addTab(new TransferFragment());
			}
		}*/
	}

	public void addTab(Fragment fragment) {
		mFragments.add(fragment);
		notifyDataSetChanged();
	}

	@Override
	public Fragment getItem(int arg0) {
	/*
		if (tabs.get(arg0).equals(Constant.ROUTE)) {
			return new RouteFragment();
		} else if (tabs.get(arg0).equals(Constant.STATION)) {
			return new PositionFragment();
		} else if (tabs.get(arg0).equals(Constant.TRANSFER)) {
			return new TransferFragment();
		}
		
		*/
		
		return null;
	}

	@Override
	public int getItemPosition(Object object) {
		return POSITION_NONE;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		super.destroyItem(container, position, object);
	}
	
	@Override
	public int getCount() {
		return tabs.size();
	}
}
