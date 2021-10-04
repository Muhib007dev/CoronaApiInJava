package com.coronaapi.coronarestapi.controller;

import java.util.ArrayList;

import com.coronaapi.coronarestapi.models.AllCountryModel;
import com.coronaapi.coronarestapi.models.CheckedCountryModel;
import com.coronaapi.coronarestapi.models.IndiaStateModel;
import com.coronaapi.coronarestapi.models.TotalInIndiaModel;
import com.coronaapi.coronarestapi.models.TotalWorldModel;
import com.coronaapi.coronarestapi.service.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {

	@Autowired
	private WebService webService;

	@Scheduled(cron = "* 1 * * * *")
	@GetMapping("/india-states-data")
	public ArrayList<IndiaStateModel> getIndiaStatesData() throws Exception {
		return webService.getIndiaStatesData();
	}

	@Scheduled(cron = "* 1 * * * *")
	@RequestMapping("/all-countries")
	public ArrayList<AllCountryModel> allCountries() throws Exception {
		return webService.allCountries();
	}

	@Scheduled(cron = "* 1 * * * *")
	@RequestMapping("/total-world")
	public TotalWorldModel totalWorld() throws Exception {
		return webService.totalWorld();
	}

	@Scheduled(cron = "* 1 * * * *")
	@GetMapping("/total-in-india")
	public TotalInIndiaModel totalInIndia() throws Exception {
		return webService.totalInIndia();
	}

	@RequestMapping("/country")
	public CheckedCountryModel checkCountry(
			@RequestParam(value = "countryname", defaultValue = "India") String countryname) throws Exception {
		return webService.checkCountry(countryname);
	}

	@RequestMapping("/state")
	public IndiaStateModel checkInState(
			@RequestParam(value = "statename", defaultValue = "Maharashtra") String stateName) throws Exception {
		return webService.checkInState(stateName);
	}
}
