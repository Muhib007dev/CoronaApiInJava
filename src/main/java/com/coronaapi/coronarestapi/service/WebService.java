package com.coronaapi.coronarestapi.service;

import java.util.ArrayList;

import com.coronaapi.coronarestapi.models.AllCountryModel;
import com.coronaapi.coronarestapi.models.CheckedCountryModel;
import com.coronaapi.coronarestapi.models.IndiaStateModel;
import com.coronaapi.coronarestapi.models.TotalInIndiaModel;
import com.coronaapi.coronarestapi.models.TotalWorldModel;

public interface WebService {
    ArrayList<IndiaStateModel> getIndiaStatesData() throws Exception;

    ArrayList<AllCountryModel> allCountries() throws Exception;

    TotalWorldModel totalWorld() throws Exception;

    TotalInIndiaModel totalInIndia() throws Exception;

    CheckedCountryModel checkCountry(String countryname) throws Exception;

    IndiaStateModel checkInState(String stateName) throws Exception;
}
