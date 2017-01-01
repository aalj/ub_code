package com.remind.bean;

/**
 * 传送的数据;
 * 在多个Activity进行传送数据时,采用全局数据形式;
 * @author Render;
 *
 */
public class TransferData {

	public enum DataType{
		BUSLINE,ALARM;
	}
	
	private static Object mData=null;
	private static TransferData mInstance=null;
	private DataType mCurrentType;
	
	private TransferData(){
		
	}
	
	public static TransferData getInstance(){
		
		if(mInstance==null){
			mInstance=new TransferData();
		}
		return mInstance;
	}
	
	public void setData(Object data,DataType dataType){
		
		mCurrentType=dataType;
		mData=data;
	}
	
	public Object getData(DataType dataType){
		
		if(dataType==mCurrentType){
			return mData;
		}
		return null;
	}
	
	/**
	 * 清空数据;
	 */
	public void clearData(){
		mData=null;
	}
}
