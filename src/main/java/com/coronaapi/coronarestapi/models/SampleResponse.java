package com.coronaapi.coronarestapi.models;

import java.util.Collection;

public class SampleResponse {
	private String state;
	private String totalConfirmedCasesIndian;
	private String totalConfirmedCasesForeign;
	private String cured;
	private String death;
	
	
	public SampleResponse(String state, String totalConfirmedCasesIndian, String totalConfirmedCasesForeign,
			String cured, String death) {
		super();
		this.state = state;
		this.totalConfirmedCasesIndian = totalConfirmedCasesIndian;
		this.totalConfirmedCasesForeign = totalConfirmedCasesForeign;
		this.cured = cured;
		this.death = death;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getTotalConfirmedCasesIndian() {
		return totalConfirmedCasesIndian;
	}
	public void setTotalConfirmedCasesIndian(String totalConfirmedCasesIndian) {
		this.totalConfirmedCasesIndian = totalConfirmedCasesIndian;
	}
	public String getTotalConfirmedCasesForeign() {
		return totalConfirmedCasesForeign;
	}
	public void setTotalConfirmedCasesForeign(String totalConfirmedCasesForeign) {
		this.totalConfirmedCasesForeign = totalConfirmedCasesForeign;
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
