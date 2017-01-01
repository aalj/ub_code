package com.remind.bean;

import java.io.Serializable;

/**
 * 闹铃对象;
 * @author Render;
 *
 */
public class MrAlarm implements Serializable{

	private static final long serialVersionUID = 1L;

	private String id;
	
	/**
	 * 当前位置的名称;
	 */
	private String positionName;
	
	/**
	 * 站点对应的经度;
	 */
	private double longtitude;
	
	/**
	 * 站点对应的纬度;
	 */
	private double latitude;
	
	
	/**
	 * 当前位置距离目的地的距离;
	 */
	private int currentDistance;
	
	/**
	 * 当前设置的闹钟是否打开;
	 */
	private boolean isOn=true;
	
	/**
	 * 该闹铃是否正在响起;
	 */
	private boolean isRing=false;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getCurrentDistance() {
		return currentDistance;
	}

	public void setCurrentDistance(int currentDistance) {
		this.currentDistance = currentDistance;
	}

	public boolean isOn() {
		return isOn;
	}

	public void setOn(boolean isOn) {
		this.isOn = isOn;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public double getLongtitude() {
		return longtitude;
	}

	public void setLongtitude(double longtitude) {
		this.longtitude = longtitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public boolean isRing() {
		return isRing;
	}

	public void setRing(boolean isRing) {
		this.isRing = isRing;
	}
}
