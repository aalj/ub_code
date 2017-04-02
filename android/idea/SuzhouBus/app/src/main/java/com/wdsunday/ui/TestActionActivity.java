package com.wdsunday.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.wdsunday.bus.R;
import com.wdsunday.utils.wight.ShapeLoadingDialog;

public class TestActionActivity extends Activity {

    ShapeLoadingDialog shapeLoadingDialog ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_action);
        shapeLoadingDialog = new ShapeLoadingDialog(this);

    }


    public void onclick(View v){shapeLoadingDialog.show();

    }

}
