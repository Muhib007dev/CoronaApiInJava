package com.coronaapi.coronarestapi.models;

public class IndiaStateModel {
    private String state;
    private String totalActiveCases;
    private String cured;
    private String death;

    public IndiaStateModel(String state, String totalActiveCases, String cured, String death) {
        super();
        this.state = state;
        this.totalActiveCases = totalActiveCases;
        this.cured = cured;
        this.death = death;
    }

    public IndiaStateModel() {
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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
