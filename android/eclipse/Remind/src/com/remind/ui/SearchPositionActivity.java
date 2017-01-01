package com.remind.ui;

import java.util.ArrayList;
import java.util.List;

import com.baidu.mapapi.search.core.CityInfo;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.PoiInfo.POITYPE;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.baidu.mapapi.search.sug.OnGetSuggestionResultListener;
import com.baidu.mapapi.search.sug.SuggestionResult;
import com.baidu.mapapi.search.sug.SuggestionSearch;
import com.baidu.mapapi.search.sug.SuggestionSearchOption;
import com.mr.remind.R;
import com.remind.adapter.PoiResultAdapter;
import com.remind.adapter.PoiResultAdapter.onPoiResultSelectedListener;
import com.remind.bean.MrPoiResult;
import com.remind.config.Constant;
import com.remind.db.AlarmColumn;
import com.remind.db.DBHelper;
import com.remind.db.PoiHistoryColumn;
import com.remind.utils.LogUtils;
import com.remind.widget.ClearEditText;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

/**
 * 搜索细节的Activity;
 * @author Render
 */
public class SearchPositionActivity extends Activity implements OnGetPoiSearchResultListener, OnGetSuggestionResultListener
      ,OnItemClickListener,onPoiResultSelectedListener{

	private static final String TAG = "SearchDetailActivity";
	
	private PoiSearch mPoiSearch = null;
	private SuggestionSearch mSuggestionSearch = null;
	private ClearEditText mEditText;
	private ListView mListView;
	private View mFooterView;
	private PoiResultAdapter mPoiResultAdapter=null;
	private List<MrPoiResult> mPoiResults=null; 
    private String mSearchName;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_detail);
		
		initView();
		initData();
	}
	
	private void initView(){
		
		mEditText=(ClearEditText) findViewById(R.id.et_search);
		mListView=(ListView) findViewById(R.id.lv_position_list);
		mFooterView=getLayoutInflater().inflate(R.layout.item_poi_result_footer, null);
		
	}
	
	private void initData(){
		
		mPoiResults=new ArrayList<MrPoiResult>();
		
		readHistoryPoi();
		
		if(mPoiResults.size()>0){
			mListView.addFooterView(mFooterView);
		}
		
		mPoiResultAdapter=new PoiResultAdapter(this, mPoiResults);
		mListView.setAdapter(mPoiResultAdapter);
		
		mPoiSearch = PoiSearch.newInstance();
		mPoiSearch.setOnGetPoiSearchResultListener(this);
		mSuggestionSearch = SuggestionSearch.newInstance();
		mSuggestionSearch.setOnGetSuggestionResultListener(this);
		
		mEditText.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				
				mSearchName=mEditText.getText().toString();
				excuteSuggestionSearch();
			}
		});
	}

	/**
	 * 开始执行建议搜索;
	 */
	private void excuteSuggestionSearch(){

		mPoiResults.clear();
		
		//清空链表;
		if(mSearchName.equals("")||mSearchName==null){
			mPoiResultAdapter.updateListView(mPoiResults,null);
			return;
		}
		
		mSuggestionSearch
		.requestSuggestion((new SuggestionSearchOption())
				.keyword(mSearchName).city(Constant.city));
		
	}
	
	@Override
	public void onGetSuggestionResult(SuggestionResult res) {
		
		if (res == null || res.getAllSuggestions() == null) {
			return;
		}
		
		for (SuggestionResult.SuggestionInfo info : res.getAllSuggestions()) {
			if (info.key != null){
				MrPoiResult poiResult=new MrPoiResult();
				
				poiResult.setCity(info.city);
				poiResult.setName(info.key);
				poiResult.setAddress(info.city+info.district);
				
				mPoiResults.add(poiResult);
			}
		}
		mPoiResultAdapter.updateListView(mPoiResults,mSearchName);
	}
	
	@Override
	public void onGetPoiDetailResult(PoiDetailResult arg0) {
		
	}

	@Override
	public void onGetPoiResult(PoiResult result) {
		
		if (result == null|| result.error == SearchResult.ERRORNO.RESULT_NOT_FOUND) {
			LogUtils.i(TAG, "poi搜索失败");
			
			//mPoiResultAdapter.updateListView(mPoiResults);
			
			return;
		}
		if (result.error == SearchResult.ERRORNO.NO_ERROR) {
			
			recordPoiInfo(result);
			
			return;
		}
		if (result.error == SearchResult.ERRORNO.AMBIGUOUS_KEYWORD) {

			// 当输入关键字在本市没有找到，但在其他城市找到时，返回包含该关键字信息的城市列表
			String strInfo = "在";
			for (CityInfo cityInfo : result.getSuggestCityList()) {
				strInfo += cityInfo.city;
				strInfo += ",";
			}
			strInfo += "找到结果";
			Toast.makeText(this, strInfo, Toast.LENGTH_LONG).show();
		}
	}
    
	/**
	 * 记录搜索的结果;
	 */
	private void recordPoiInfo(PoiResult result){
		
		List<PoiInfo> poiInfos=result.getAllPoi();
		
		if(poiInfos==null){
			return;
		}
		
		for(PoiInfo poiInfo:poiInfos){
			
			MrPoiResult poiResult=new MrPoiResult();
			
			poiResult.setCity(poiInfo.city);
			poiResult.setName(poiInfo.name);
			poiResult.setAddress(poiInfo.address);
			poiResult.setLatitude(poiInfo.location.latitude);
			poiResult.setLongtitude(poiInfo.location.longitude);
			
			POITYPE poiType=poiInfo.type;
			
			if(poiType==POITYPE.BUS_STATION){
				poiResult.setPoiType(MrPoiResult.BUS_STATION);
			}else if(poiType==POITYPE.POINT){
				poiResult.setPoiType(MrPoiResult.POINT);
			}else if(poiType==POITYPE.BUS_LINE){
				poiResult.setPoiType(MrPoiResult.BUS_LINE);
			}else if(poiType==POITYPE.SUBWAY_STATION){
				poiResult.setPoiType(MrPoiResult.SUBWAY_STATION);
			}else if(poiType==POITYPE.SUBWAY_LINE){
				poiResult.setPoiType(MrPoiResult.SUBWAY_LINE);
			}
			mPoiResults.add(poiResult);
		}
		
		//mPoiResultAdapter.updateListView(mPoiResults);
	}
	
	/**
	 * 读取历史数据;
	 */
	private void readHistoryPoi(){
		
		DBHelper dbHelper=DBHelper.getInstance(this);
		
		Cursor cursor=dbHelper.query(PoiHistoryColumn.TABLE_NAME, null, null, null);
		
		int nIndex=0;
	    while(cursor.moveToNext())  
	    {  
	    	MrPoiResult poiResult=new MrPoiResult();
	    	
	    	nIndex=cursor.getColumnIndex(PoiHistoryColumn._ID);
	    	String id=cursor.getString(nIndex);
	    	poiResult.setId(id);
	    	
	    	nIndex=cursor.getColumnIndex(PoiHistoryColumn.NAME);
	    	String name=cursor.getString(nIndex);
	    	poiResult.setName(name);
	    	
	    	nIndex=cursor.getColumnIndex(PoiHistoryColumn.CITY);
	    	String city=cursor.getString(nIndex);
	    	poiResult.setCity(city);
	    	
	    	nIndex=cursor.getColumnIndex(PoiHistoryColumn.ADDRESS);
	    	String address=cursor.getString(nIndex);
	    	poiResult.setAddress(address);
	    	
	    	nIndex=cursor.getColumnIndex(AlarmColumn.LONGTITUDE);
	    	double longtitude=cursor.getDouble(nIndex);
	    	poiResult.setLongtitude(longtitude);
	    	
	    	nIndex=cursor.getColumnIndex(AlarmColumn.LATITUDE);
	    	double latitude=cursor.getDouble(nIndex);
	    	poiResult.setLatitude(latitude);
	    	
	    	mPoiResults.add(poiResult);
	    }
	}
	
	@Override
	protected void onDestroy() {
		
		mPoiSearch.destroy();
		mSuggestionSearch.destroy();
		super.onDestroy();
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
	}

	@Override
	public void onPoiResultSelected(MrPoiResult poiResult) {
		
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {

	}
}
