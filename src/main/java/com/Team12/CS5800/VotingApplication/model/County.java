package com.Team12.CS5800.VotingApplication.model;

public class County {
	private int countyID;
	private String countyName;
	private int congressionalDistrict;
	
	public County(int countyID, String countyName, int congressionalDistrict) {
		this.countyID = countyID;
		this.countyName = countyName;
		this.congressionalDistrict = congressionalDistrict;
	}

	public int getCountyID() {
		return countyID;
	}

	public void setCountyID(int countyID) {
		this.countyID = countyID;
	}

	public String getCountyName() {
		return countyName;
	}

	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}

	public int getCongressionalDistrict() {
		return congressionalDistrict;
	}

	public void setCongressionalDistrict(int congressionalDistrict) {
		this.congressionalDistrict = congressionalDistrict;
	}
	
	
}
