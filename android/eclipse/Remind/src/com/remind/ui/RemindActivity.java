package com.remind.ui;

import android.os.Bundle;

import com.mr.remind.R;
import com.remind.ui.base.BaseFragmentActivity;
import com.remind.ui.fragment.RemindFragment;

public class RemindActivity extends BaseFragmentActivity {

	private RemindFragment remindFragment;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_remind);

		remindFragment=new RemindFragment();
	
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
				.add(R.id.container, remindFragment).commit();
		}
	}
}
