package net.lll0.bus.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import net.lll0.bus.utils.ObjectUtils;

import java.util.List;

/**
 * Created by liang on 2017/8/24.
 */

public abstract class RecyclerViewNotHeadFootAdapter<T extends BaseBean> extends RecyclerView.Adapter<RecyclerViewHolder> {
    /**
     * 加载不同的两种方式
     * 1. 传入不同的数据源，对应的位置加载不同的布局
     * 2. 传入一个数据源，但是通过数据源里面特殊的字段判断加载什么布局
     */
    private List<T> bean;
    private Context mContext;

    private OnItemClickListener mClickListener;
    private OnItemLongClickListener mLongClickListener;

    public RecyclerViewNotHeadFootAdapter(List<T> bean, Context mContext) {
        this.bean = bean;
        this.mContext = mContext;
    }

    @Override
    public int getItemViewType(int position) {
        //通过这个区分加载不同 view
        //通过判断特殊的字段加载不同的布局

        return bean.get(position).getType();
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //通过 getTtemViewType 返回的 内容加载不同 的布局

        //假设加载 三中布局分别对应 1 2 3
        final RecyclerViewHolder recyclerViewHolder = myCreateViewHolder(parent, viewType);


        if (mClickListener != null) {


            recyclerViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mClickListener.onItemClick(recyclerViewHolder.itemView, recyclerViewHolder.getLayoutPosition());
                }
            });
        }
        if (mLongClickListener != null) {
            recyclerViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mLongClickListener.onItemLongClick(recyclerViewHolder.itemView,recyclerViewHolder.getLayoutPosition());
                }
            });
        }


        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        //onCreateViewHolder 为不同的布局绑定对应的数据
        myBindViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        //主要是计算 加载数据的总数
        return bean.size();
    }


    /**
     * 调用item的点击事件就是调用这个方法，然后实现监听事件的接口
     *
     * @param listener
     */
    final public void setOnItemClickListener(OnItemClickListener listener) {
        mClickListener = listener;
    }

    /**
     * 调用item长的点击事件就是调用这个方法，然后实现监听事件的接口
     *
     * @param listener
     */
    final public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        mLongClickListener = listener;
    }

    abstract protected RecyclerViewHolder myCreateViewHolder(ViewGroup parent, int viewType);

    abstract protected void myBindViewHolder(RecyclerViewHolder holder, int position);


    /**
     * 实现每一个item的点击事件需要实现的方法
     */
    public interface OnItemClickListener {
        void onItemClick(View itemView, int pos);
    }

    /**
     * 实现item的长顶啊及事件需要实现的方法
     */
    public interface OnItemLongClickListener {
        void onItemLongClick(View itemView, int pos);
    }

    public void addList(List<T> list){
        if (!ObjectUtils.isBlank(list)) {
            bean.clear();
            bean.addAll(list);
            notifyDataSetChanged();
        }
    }

}
