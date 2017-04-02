package com.wdsunday.utils.wight;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.wdsunday.bus.R;

/**
 * Created by bumu-zhz on 2016/5/19.
 */
public class WarnDialog extends Dialog {



	public WarnDialog(Context context) {
		super(context, R.style.commonConfirmDialog);



	}



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_dialog);
		setCanceledOnTouchOutside(true);

//		initView();
	}

//	private void initView() {
//		contentView = (TextView) findViewById(R.id.dialog_warn_content);
//		if (StringUtil.isEmpty(mcontent)) {
//			contentView.setText("");
//			contentView.setVisibility(View.GONE);
//		} else {
//			contentView.setText(mcontent);
//			contentView.setVisibility(View.VISIBLE);
//		}
//
//		confirmBut = (TextView) findViewById(R.id.dialog_warn_confirm);
//		confirmBut.setOnClickListener(this);
//		if (!StringUtil.isEmpty(mConfirmText)) {
//			confirmBut.setText(mConfirmText);
//		}
//
//		if (mConfirmColor != 0) {
//			confirmBut.setTextColor(mcontext.getResources().getColor(mConfirmColor));
//		}
//	}

}
