package com.coronaapi.coronarestapi.models;

public class TotalInIndiaModel {

	String totalConfirmedCases;
	String cured;
	String death;
	
	
	
	
	
	public TotalInIndiaModel(String totalConfirmedCases, String cured, String death) {
		super();
		this.totalConfirmedCases = totalConfirmedCases;
		this.cured = cured;
		this.death = death;
	}
	public String getTotalConfirmedCases() {
		return totalConfirmedCases;
	}
	public void setTotalConfirmedCases(String totalConfirmedCases) {
		this.totalConfirmedCases = totalConfirmedCases;
	}
	public String getCured() {
		return cured;
	}
	public void setCured(String cured) {
		this.cured = cured;
	}
	public String getDeath() {
		return death;
	}
	public void setDeath(String death) {
		this.death = death;
	}
	
	
}
