package com.lancer.entity;

import java.sql.Timestamp;

public class TemperatureEntity {

	private float temperature;
	
	private boolean isValid;
	
	private Timestamp time;
	
	private String devID;

	public float getTemperature() {
		return temperature;
	}

	public boolean isValid() {
		return isValid;
	}

	public Timestamp getTime() {
		return time;
	}

	public String getDevID(){
		return devID;
	}
	
	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}
	
	
}
