package com.coronaapi.coronarestapi.models;

public class TotalInIndiaModel {

	String totalActiveCases;
	String cured;
	String death;
	
	
	
	
	
	public TotalInIndiaModel(String totalActiveCases, String cured, String death) {
		super();
		this.totalActiveCases = totalActiveCases;
		this.cured = cured;
		this.death = death;
	}

    public TotalInIndiaModel() {

    }

    public String getTotalActiveCases() {
		return totalActiveCases;
	}
	public void setTotalActiveCases(String totalActiveCases) {
		this.totalActiveCases = totalActiveCases;
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
