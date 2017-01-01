package com.remind.bean;

/**
 * poi搜索结果对象;
 * @author Render
 */
public class MrPoiResult {
	
	public final static String POINT="point";
	public final static String BUS_STATION="bus_station";
	public final static String BUS_LINE="bus_line";
	public final static String SUBWAY_STATION="subway_station";
	public final static String SUBWAY_LINE="subway_line";
	
	private String id;
	
	private String name;
	
	private String address;
	
	private String city;
	
	private double longtitude;
	
	private double latitude;

	private String poiType;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public String getCity() {
		return city;
	}

	public double getLongtitude() {
		return longtitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setLongtitude(double longtitude) {
		this.longtitude = longtitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public String getPoiType() {
		return poiType;
	}

	public void setPoiType(String poiType) {
		this.poiType = poiType;
	}
}
