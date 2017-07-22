package net.lll0.bus.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import net.lll0.bus.suzhoubus.R;
import net.lll0.bus.utils.wight.ShapeLoadingDialog;


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
