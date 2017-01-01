package com.remind.db;

import java.util.HashMap;
import java.util.Map;
import android.net.Uri;

/**
 * 搜索的历史信息;
 * @author Render
 */
public class PoiHistoryColumn extends DatabaseColumn{

	public static final String TABLE_NAME="poi_history";
	public static final Uri    CONTENT_URI=Uri.parse("content://"+AUTHORITY+"/"+TABLE_NAME);
	
	//数据库字段常量;
	public static final String NAME="name";
	public static final String CITY="city";
	public static final String ADDRESS="address";
	public static final String LONGTITUDE="longtitude";
	public static final String LATITUDE="latitude";
	
	//定义表字段与数据库操作字段的映射表;
	private static final Map<String, String> mColumnMap=new HashMap<String,String>();
	
	static{
		mColumnMap.put(_ID, "integer primary key autoincrement");
		mColumnMap.put(NAME, "text not null");
		mColumnMap.put(CITY, "text not null");
		mColumnMap.put(ADDRESS, "text not null");
		mColumnMap.put(LONGTITUDE, "float not null");
		mColumnMap.put(LATITUDE, "float not null");
	}
	
	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return  TABLE_NAME;
	}

	@Override
	public Uri getTableContent() {
		// TODO Auto-generated method stub
		return CONTENT_URI;
	}

	@Override
	protected Map<String, String> getTableMap() {
		// TODO Auto-generated method stub
		return mColumnMap;
	}

}
