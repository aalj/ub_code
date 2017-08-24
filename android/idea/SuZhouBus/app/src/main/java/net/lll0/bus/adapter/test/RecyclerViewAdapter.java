package net.lll0.bus.adapter.test;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by liang on 2017/8/24.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {


    /**
     * 加载不同的两种方式
     * 1. 传入不同的数据源，对应的位置加载不同的布局
     * 2. 传入一个数据源，但是通过数据源里面特殊的字段判断加载什么布局
     */


    @Override
    public int getItemViewType(int position) {
        //通过这个区分加载不同 view
        //通过判断特殊的字段加载不同的布局


        return super.getItemViewType(position);
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //通过 getTtemViewType 返回的 内容加载不同 的布局
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {

        //onCreateViewHolder 为不同的布局绑定对应的数据

    }

    @Override
    public int getItemCount() {
        //主要是计算 加载数据的总数
        return 0;
    }
}
