package com.remind.widget;

import com.mr.remind.R;
import com.remind.utils.LogUtils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * 左右滑动的button;
 * @author Render
 *
 */
public class Slidebutton extends View {

	private Bitmap slideBitmap;  //滑动图片;
	private Bitmap swtichBitmap; //背景图片;
	
	private int slideBitmapWidth; //滑动图片宽度;
	private int switchBitmapWidth; //背景图片宽度;
	private int switchBitmapHeight; //背景图片高度;
	
	private boolean currentState=false;  //开关状态，false为开,true 为关; 
	private boolean isSliding=false; //是否正在滑动中;
	
	private int currentX; //当前开关位置;
	
	private OnToggleStateChangedListener mChangedListener;
	
	public Slidebutton(Context context) {
		this(context,null);
		
	}

	public Slidebutton(Context context,AttributeSet attrs){
		this(context,attrs,0);
		
	}
	
	public Slidebutton(Context context,AttributeSet attrs,int defStyleAttr){
		super(context, attrs, defStyleAttr);
		initBitmap();
	}
	
	private void initBitmap(){
		
		slideBitmap=BitmapFactory.decodeResource(getResources(), 
				R.drawable.slidebutton);
		swtichBitmap=BitmapFactory.decodeResource(getResources(), 
				R.drawable.slidebutton_background);
		
		slideBitmapWidth = slideBitmap.getWidth();  
        switchBitmapWidth = swtichBitmap.getWidth();  
        switchBitmapHeight = swtichBitmap.getHeight();  
        
        LogUtils.i("switchBitmapWidth", switchBitmapWidth + "");  
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		setMeasuredDimension(switchBitmapWidth, switchBitmapHeight); //设置控件宽高;
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		
		//绘制button背景图片;
		canvas.drawBitmap(swtichBitmap, 0, 0, null);
		
		if(isSliding){
			int bgLimit=switchBitmapWidth-slideBitmapWidth;
			
			if(currentX>bgLimit){
				currentX=bgLimit;
			}
			canvas.drawBitmap(slideBitmap, currentX, 0,null);
		}else {
			if (currentState) { // 绘制开关为开的状态  
                canvas.drawBitmap(slideBitmap, switchBitmapWidth  
                          - slideBitmapWidth, 0, null);  
           } else { // 绘制开关为关的状态  
                canvas.drawBitmap(slideBitmap, 0, 0, null);  
           }  
		}
		
		super.onDraw(canvas);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		//手势识别 判断滑动方向
		int action=event.getAction();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			isSliding=true;
			currentX=(int)event.getX();
			break;
		
		case MotionEvent.ACTION_MOVE:
			currentX=(int)event.getX();
			LogUtils.i("currentX",currentX+"");
			break;
		
		case MotionEvent.ACTION_UP:
			isSliding=false;
			int bgLimit=switchBitmapWidth-slideBitmapWidth;
			boolean state=currentX>bgLimit;
			if(state!=currentState&&mChangedListener!=null){
				mChangedListener.onToggleStateChanged(state);
			}
			
			//如果状态没有改变,则重置到开始状态;
			if(state==false){
				currentX=0;
			}
			
			currentState=state;
			break;
		default:
			break;
		}
		
		invalidate();
		
		return true;
	}
	
	public OnToggleStateChangedListener getChangedListener(){
		return mChangedListener;
	}
	
	public void setChangedListener(
			OnToggleStateChangedListener mChangedListener){
		this.mChangedListener=mChangedListener;
	}
	
	public boolean isToggleState(){
		return currentState;
	}
	
	public void setToggleState(boolean currentState){
		this.currentState=currentState;
	}
	
	public interface OnToggleStateChangedListener{
		
		public void onToggleStateChanged(boolean state);
	}
}
















