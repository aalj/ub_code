package net.lll0.bus.ui.test_activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import net.lll0.bus.suzhoubus.R;

public class RecyclerViewTestActivity extends Activity {

    private RecyclerView mRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_test);
        initView();
    }

    private void initView() {
        mRecycler = (RecyclerView) findViewById(R.id.recycler);
    }
}
