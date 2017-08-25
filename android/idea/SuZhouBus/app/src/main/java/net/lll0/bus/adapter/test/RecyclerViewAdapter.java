package net.lll0.bus.adapter.test;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.lll0.bus.suzhoubus.R;
import net.lll0.bus.adapter.RecyclerViewHolder;

import java.util.List;

/**
 * Created by liang on 2017/8/24.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

    private String typeView01 = "item1";
    private String typeView02 = "item2";


    /**
     * 加载不同的两种方式
     * 1. 传入不同的数据源，对应的位置加载不同的布局
     * 2. 传入一个数据源，但是通过数据源里面特殊的字段判断加载什么布局
     */
    private List<Bean> bean;
    private Context mContext;

    public RecyclerViewAdapter(List<Bean> bean, Context mContext) {
        this.bean = bean;
        this.mContext = mContext;
    }

    @Override
    public int getItemViewType(int position) {
        //通过这个区分加载不同 view
        //通过判断特殊的字段加载不同的布局

        return bean.get(position).type;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //通过 getTtemViewType 返回的 内容加载不同 的布局

        //假设加载 三中布局分别对应 1 2 3

        if (1 == viewType) {
            return new RecyclerViewHolder(mContext,LayoutInflater.from(mContext).inflate(R.layout.item, parent, false), viewType);
        } else if (2 == viewType) {
            return new RecyclerViewHolder(mContext,LayoutInflater.from(mContext).inflate(R.layout.item_lineinfo, parent, false), viewType);
        } else if (3 == viewType) {
            return new RecyclerViewHolder(mContext,LayoutInflater.from(mContext).inflate(R.layout.nav_header_home, parent, false), viewType);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        //onCreateViewHolder 为不同的布局绑定对应的数据
        Bean bean = this.bean.get(position);
        int type = bean.type;
        if (1 == type) {
            holder.setText(R.id.textView2,"textView2");
            holder.setText(R.id.textView3,"textView3");
        } else if (2 == type) {
            holder.setText(R.id.lineinfo_index,position+"");
        } else if (3 == type) {

        }


    }

    @Override
    public int getItemCount() {
        //主要是计算 加载数据的总数
        return bean.size();
    }
}
