package com.coronaapi.coronarestapi.models;

public class CheckStateModel {
	private String state;
	private String totalConfirmedCases;
	private String cured;
	private String death;
	public CheckStateModel(String state, String totalConfirmedCases, String cured, String death) {
		super();
		this.state = state;
		this.totalConfirmedCases = totalConfirmedCases;
		this.cured = cured;
		this.death = death;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
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
