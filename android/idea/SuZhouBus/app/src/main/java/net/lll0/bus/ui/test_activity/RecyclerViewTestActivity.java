package net.lll0.bus.ui.test_activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import net.lll0.bus.adapter.test.Bean;
import net.lll0.bus.adapter.test.RecyclerViewAdapter;
import net.lll0.bus.suzhoubus.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewTestActivity extends Activity {

    private RecyclerView mRecycler;
    private Activity mActitivity;
    private RecyclerViewAdapter recyclerViewAdapter;
    private List<Bean> beans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_test);
        mActitivity = this ;
       initData();
        initView();
    }

    private void initData() {
        beans = new ArrayList<>();
        Bean bean ;
        for (int i = 0; i < 10; i++) {
            bean = new Bean();
            if (i==0) {
                bean.setType(1);
                beans.add(bean);
                continue;
            }else if(i==9){
                bean.setType(3);
                beans.add(bean);
                continue;
            }
            bean.setType(2);
            beans.add(bean);
        }
    }

    private void initView() {
        mRecycler = (RecyclerView) findViewById(R.id.recycler);
        mRecycler.setLayoutManager(new LinearLayoutManager(mActitivity));
        recyclerViewAdapter = new RecyclerViewAdapter(beans,mActitivity);
        mRecycler.setAdapter(recyclerViewAdapter);
    }
}
