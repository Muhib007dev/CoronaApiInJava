package com.coronaapi.coronarestapi.models;

public class TotalModel {
	String total;
	String death;
	String recovered;
	String active;

	public TotalModel(String total, String death, String recovered, String active) {
		super();
		this.total = total;
		this.death = death;
		this.recovered = recovered;
		this.active = active;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getDeath() {
		return death;
	}

	public void setDeath(String death) {
		this.death = death;
	}

	public String getRecovered() {
		return recovered;
	}

	public void setRecovered(String recovered) {
		this.recovered = recovered;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

}
