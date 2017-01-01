package com.remind.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mr.remind.R;
import com.remind.adapter.base.AdapterBase;
import com.remind.bean.MrPoiResult;

public class PoiResultAdapter extends AdapterBase<MrPoiResult>{

	private Context ct;
	private ViewHolder viewHolder=null;
	private String mSearchKey;
	private RelativeLayout.LayoutParams lParamsCenter;
	private RelativeLayout.LayoutParams lParamsTop;
	
	private onPoiResultSelectedListener mListener=null;
	
	public PoiResultAdapter(Context ct,List<MrPoiResult> postions){
		this.ct=ct;
		appendToList(postions);
		
		lParamsCenter=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		lParamsCenter.addRule(RelativeLayout.CENTER_VERTICAL);
		
		lParamsTop=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		lParamsTop.addRule(RelativeLayout.ALIGN_PARENT_TOP);
	}

	public void updateListView(List<MrPoiResult> datas,String searchKey){

		mSearchKey=searchKey;
		
		clear();
		appendToList(datas);
		notifyDataSetChanged();
	}
	
	static class ViewHolder {
		
		ImageView imageView;
		TextView tvName;
		TextView tvAddress;
	}

	/**
	 * 设置监听器;
	 * @param listener
	 */
	public void setPoiResultSelectedListener(onPoiResultSelectedListener listener) {
		this.mListener = listener;
	}

	@Override
	protected View getExView(int position, View convertView, ViewGroup parent) {
		
		MrPoiResult poiResult=getList().get(position);
		
		if (convertView == null) {

			convertView = LayoutInflater.from(ct).inflate(R.layout.item_poi_result, null);
			viewHolder = new ViewHolder();
		
			viewHolder.imageView = (ImageView) convertView.findViewById(R.id.iv_postion_select_arrow);
			viewHolder.tvName=(TextView)convertView.findViewById(R.id.tv_position_name);
			viewHolder.tvAddress=(TextView)convertView.findViewById(R.id.tv_position_address);
			
			convertView.setTag(viewHolder);
			
		} else{
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		//添加事件点击相应;
		viewHolder.imageView.setOnClickListener(new onPoiResultSelectImageClick(poiResult));
		
		String name=poiResult.getName();
		int nBegIndex=name.indexOf(mSearchKey);
		int nEndIndex=nBegIndex+mSearchKey.length();
		
		if(nBegIndex!=-1)
		{
			//设置指定位置文字的颜色
			SpannableStringBuilder style=new SpannableStringBuilder(name);   
			style.setSpan(new ForegroundColorSpan(Color.rgb(118, 173, 255)),
					nBegIndex,nEndIndex,Spannable.SPAN_EXCLUSIVE_INCLUSIVE);    
			viewHolder.tvName.setText(style);
		}else {
			viewHolder.tvName.setText(name);
		}

		if(poiResult.getAddress()==null||poiResult.getAddress().equals("")){
			viewHolder.tvName.setLayoutParams(lParamsCenter);
			viewHolder.tvAddress.setText("");
		}else{
			viewHolder.tvName.setLayoutParams(lParamsTop);
			viewHolder.tvAddress.setText(poiResult.getAddress());
		}
		return convertView;
	}

	@Override
	protected void onReachBottom() {
	
	}
	
	//响应右边的位置选择点击事件;
	public interface onPoiResultSelectedListener{
		
		public void onPoiResultSelected(MrPoiResult poiResult);
	}
	
	public class onPoiResultSelectImageClick implements OnClickListener{

		MrPoiResult mPoiResult=null;
		
		public onPoiResultSelectImageClick(MrPoiResult poiResult) {
			
			this.mPoiResult=poiResult;
		}
		
		@Override
		public void onClick(View v) {
			
			if(viewHolder==null||mListener==null){
				return;
			}
			
			//回调哦;
			mListener.onPoiResultSelected(mPoiResult);		
		}
	}
}
