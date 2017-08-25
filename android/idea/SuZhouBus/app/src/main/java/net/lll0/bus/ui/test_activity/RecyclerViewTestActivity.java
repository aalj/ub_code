package net.lll0.bus.ui.test_activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import net.lll0.bus.adapter.RecyclerViewHolder;
import net.lll0.bus.adapter.BaseBean;
import net.lll0.bus.adapter.RecyclerViewNotHeadFootAdapter;
import net.lll0.bus.suzhoubus.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewTestActivity extends Activity implements RecyclerViewNotHeadFootAdapter.OnItemClickListener {

    private RecyclerView mRecycler;
    private Activity mActitivity;
    private RecyclerViewNotHeadFootAdapter recyclerViewNotHeadFootAdapter;
    private List<BaseBean> beans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_test);
        mActitivity = this;
        initData();
        initView();
    }

    private void initData() {
        beans = new ArrayList<>();
        BaseBean bean;
        for (int i = 0; i < 10; i++) {
            bean = new BaseBean();
            if (i == 0) {
                bean.setType(1);
                beans.add(bean);
                continue;
            } else if (i == 9) {
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
        recyclerViewNotHeadFootAdapter = new RecyclerViewNotHeadFootAdapter<BaseBean>(beans, mActitivity) {
            @Override
            protected RecyclerViewHolder myCreateViewHolder(ViewGroup parent, int viewType) {
                RecyclerViewHolder recyclerViewHolder = null;
                if (1 == viewType) {
                    recyclerViewHolder = new RecyclerViewHolder(mActitivity, LayoutInflater.from(mActitivity).inflate(R.layout.item, parent, false), viewType);
                } else if (2 == viewType) {
                    recyclerViewHolder = new RecyclerViewHolder(mActitivity, LayoutInflater.from(mActitivity).inflate(R.layout.item_lineinfo, parent, false), viewType);
                } else if (3 == viewType) {
                    recyclerViewHolder = new RecyclerViewHolder(mActitivity, LayoutInflater.from(mActitivity).inflate(R.layout.nav_header_home, parent, false), viewType);
                }
                return recyclerViewHolder;
            }

            @Override
            protected void myBindViewHolder(RecyclerViewHolder holder, int position) {
                BaseBean bean = beans.get(position);
                int type = bean.getType();
                if (1 == type) {
                    holder.setText(R.id.textView2, "textView2");
                    holder.setText(R.id.textView3, "textView3");
                } else if (2 == type) {
                    holder.setText(R.id.lineinfo_index, position + "");
                } else if (3 == type) {

                }
            }
        };
        mRecycler.setAdapter(recyclerViewNotHeadFootAdapter);
        recyclerViewNotHeadFootAdapter.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(View itemView, int pos) {
        Toast.makeText(mActitivity,pos+"",Toast.LENGTH_SHORT).show();
    }
}
