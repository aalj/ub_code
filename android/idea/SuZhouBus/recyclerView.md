[原文首发  [http://blog.lll0.net/post/recyclerview.html](http://blog.lll0.net/post/recyclerview.html)]([http://blog.lll0.net/post/recyclerview.html](http://blog.lll0.net/post/recyclerview.html))

> 强大而高效的 `RecyclerView`  

`ListView`作为一个强大而有使用频繁的控件，但是随着我们业务的发展慢慢的就感觉 `ListView` 在某些业务场景中已经不能满足我们的业务需求。举个栗子：如果我们在一个页面上需要加载不同的布局，在列表中间某一行加载一个广告试图，而这个广告的布局和整个个列表的布局样式是完全不一样的。如果放在`ListView` 中，这种布局是不太好实现的。但是放在Google 提出的新控件 ReyclerView 中 实现这种布局是非常简单的。

> RecyclerView 是Android L版本中新添加的一个用来取代ListView的SDK，它的灵活性与可替代性比listview更好。

### 基本介绍

在使用RecyclerView中引入了几个相关的类

1. `LayoutManager`  用来确定每一个item如何进行排列摆放，何时展示和隐藏。回收或重用一个View的时候，LayoutManager会向适配器请求新的数据来替换旧的数据，这种机制避免了创建过多的View和频繁的调用findViewById方法（与ListView原理类似）。Google 为我们提供了 几个基础的布局
   1. LinearLayoutManager  线性布局布局样式和ListView一样 呈现的是线性
   2. GridLayoutManager 表格布局与GridView 一样
   3. StaggeredGridLayoutManager  瀑布流布局 这是新的布局 简单描述就是加载的每个item在页面上占有的空间都是不一样的布局
2. `RecyclerView.Adapter`  RecyclerView 的适配器 数据的加载和item 的绑定都是通过这个类来实现。在使用的时候需要继承该类来进行相应的处理
3. `RecyclerView.ViewHolder`  同样是使用中需要继承该类，然后进行把数据和item里面的布局进行绑定

在使用的时候 其实不用考虑复用的问题。

### 简单使用

1.添加依赖在`build.gradle` 中添加依赖 ，然后同步一下 引入依赖需要的包

```java
dependencies {
     compile 'com.android.support:recyclerview-v7:25.1.1' 
}
```



2.布局中使用RecylerView

```xml
<?xml version="1.0" encoding="utf-8"?>
<android.support.constraint.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="net.lll0.bus.ui.test_activity.RecyclerViewTestActivity">
    <android.support.v7.widget.RecyclerView
        android:id="@+id/recycler"
        android:layout_width="368dp"
        android:layout_height="551dp"
        app:layout_constraintBottom_toBottomOf="parent"
        android:layout_marginBottom="8dp"
        android:layout_marginLeft="8dp"
        app:layout_constraintLeft_toLeftOf="parent"
        android:layout_marginRight="8dp"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        android:layout_marginTop="8dp" />
</android.support.constraint.ConstraintLayout>
```



3.创建布局之后需要在Activity中获得这个控件，并声明`LayoutManager`与`Adapter`，代码如下

```java
 mRecycler = (RecyclerView) findViewById(R.id.recycler);
 mRecycler.setLayoutManager(new LinearLayoutManager(mActitivity));
 recyclerViewAdapter = new RecyclerViewAdapter(beans,mActitivity);
 mRecycler.setAdapter(recyclerViewAdapter);
```

4.Adapter的创建 

```java
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

```

5.RecyclerView.ViewHolder 子类的实现  ，这是一个通用的ViewHolder

```java
package net.lll0.bus.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
/**
 * Created by liang on 2016/2/15.
 */
public class RecyclerViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> mViews;//集合类，layout里包含的View,以view的id作为key，value是view对象
    private Context mContext;//上下文对象
    private int type;

    public RecyclerViewHolder(Context ctx, View itemView) {
        super(itemView);
        mContext = ctx;
        mViews = new SparseArray<View>();
    }
    public RecyclerViewHolder(Context ctx, View itemView,int type) {
        super(itemView);
        mContext = ctx;
        mViews = new SparseArray<View>();
        this.type = type;
    }
    public View getItemView() {
        return itemView;
    }
    /*
     * 通过空间id在SparseArray集合中找出用户View
     * @param viewId  控件的id
     * @param <T>  具体的是那个控件
     * @return  当然是返回你要找的控件了
     */
    private <T extends View> T findViewById(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }
    /**
     * 通过findViewById以及控件id的到用户的空间对象
     *
     * @param viewId
     * @return
     */
    public View getView(int viewId) {
        return findViewById(viewId);
    }
    public TextView getTextView(int viewId) {
        return (TextView) getView(viewId);
    }
    public Button getButton(int viewId) {
        return (Button) getView(viewId);
    }
    public ImageView getImageView(int viewId) {
        return (ImageView) getView(viewId);
    }
    public ImageButton getImageButton(int viewId) {
        return (ImageButton) getView(viewId);
    }
    public EditText getEditText(int viewId) {
        return (EditText) getView(viewId);
    }
    public RecyclerViewHolder setText(int viewId, String value) {
        TextView view = findViewById(viewId);
        view.setText(value);
        return this;
    }
    public RecyclerViewHolder setBackground(int viewId, int resId) {
        View view = findViewById(viewId);
        view.setBackgroundResource(resId);
        return this;
    }
    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }
    /**
     * 通过该方法可以的到对应控件的点击事件
     *
     * @param viewId   控件的id
     * @param listener 需要实现的监听器
     * @return
     */
    public RecyclerViewHolder setClickListener(int viewId, View.OnClickListener listener) {
        View view = findViewById(viewId);
        view.setOnClickListener(listener);
        return this;
    }
}
```

### 运行

只要实现以上代码，就能实现具体的内容。