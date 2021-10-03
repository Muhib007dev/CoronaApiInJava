package com.coronaapi.coronarestapi.controller;

import com.coronaapi.coronarestapi.helper.StaticHelper;
import com.coronaapi.coronarestapi.models.*;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;
import org.apache.commons.lang3.StringUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class WebController {

	@Scheduled(cron = "* 1 * * * *")
	@GetMapping("/india-states-data")
	public ArrayList<IndiaStateModel> getIndiaStatesData() throws Exception {
		
		List<HtmlTableRow> rows = StaticHelper.getMoHFWData();
		ArrayList<IndiaStateModel> responseList = new ArrayList<>();
		for (int i = 0; i < 35; i++) {
			responseList.add(new IndiaStateModel(rows.get(i).getCell(1).asText(), rows.get(i).getCell(2).asText(),
					rows.get(i).getCell(4).asText(), rows.get(i).getCell(6).asText()));
		}
		
		return responseList;
	}

	@Scheduled(cron = "* 1 * * * *")
	@RequestMapping("/all-countries")
	public ArrayList<AllCountryModel> all() throws Exception {
		
		final HtmlTable table = StaticHelper.getWorldTable();
		ArrayList<AllCountryModel> arrayList = new ArrayList<>();
		for (int i = 9; i < 224; i++) {
			for (int j = 0; j < 1; j++) {
				arrayList.add(new AllCountryModel(table.getCellAt(i, j + 1).asText(),
						table.getCellAt(i, j + 2).asText(), table.getCellAt(i, j + 4).asText(),
						table.getCellAt(i, j + 6).asText(), table.getCellAt(i, j + 8).asText()));
			}
		}
		return arrayList;
	}

	@Scheduled(cron = "* 1 * * * *")
	@RequestMapping("/total-world")
	public TotalModel total() throws Exception {
		
		final HtmlTable table = StaticHelper.getWorldTable();
		TotalModel model = null;
		for (int i = 8; i < 9; i++) {
			for (int j = 0; j < 1; j++) {
				model = new TotalModel(table.getCellAt(i, j + 2).asText(), table.getCellAt(i, j + 4).asText(),
						table.getCellAt(i, j + 6).asText(), table.getCellAt(i, j + 8).asText());
			}
		}
		return model;
	}

	@Scheduled(cron = "* 1 * * * *")
	@GetMapping("/total-in-india")
	public TotalInIndiaModel totalInIndia() throws Exception {

		HtmlTableRow totalsRow = StaticHelper.getMoHFWData().get(35);
		TotalInIndiaModel totalInIndia = new TotalInIndiaModel();
		totalInIndia.setTotalActiveCases(totalsRow.getCell(1).asText());
		totalInIndia.setCured(totalsRow.getCell(3).asText());
		totalInIndia.setDeath(totalsRow.getCell(5).asText());

		return totalInIndia;
	}

	@RequestMapping("/country")
	public CheckedCountryModel checkCountry(
			@RequestParam(value = "countryname", defaultValue = "India") String countryname) throws Exception {

		final HtmlTable table = StaticHelper.getWorldTable();
		CheckedCountryModel countryModel = null;
		for (int i = 9; i < 224; i++) {
			for (int j = 0; j < 1; j++) {
				if (StringUtils.containsIgnoreCase(countryname, table.getCellAt(i, j + 1).asText())) {
					countryModel = new CheckedCountryModel(table.getCellAt(i, j + 1).asText(),
							table.getCellAt(i, j + 2).asText(), table.getCellAt(i, j + 4).asText(),
							table.getCellAt(i, j + 6).asText(), table.getCellAt(i, j + 8).asText());
				}

			}
		}
		return countryModel;
	}

	@RequestMapping("/state")
	public IndiaStateModel checkInState(
			@RequestParam(value = "statename", defaultValue = "Maharashtra") String stateName) throws Exception {

		List<HtmlTableRow> mofhwData = StaticHelper.getMoHFWData();
		IndiaStateModel checkStateModel = new IndiaStateModel();
		for (int i = 0; i < 35; i++) {
			if (mofhwData.get(i).getCell(1).asText().equalsIgnoreCase(stateName)) {
				checkStateModel.setState(stateName);
				checkStateModel.setTotalActiveCases(mofhwData.get(i).getCell(2).asText());
				checkStateModel.setCured(mofhwData.get(i).getCell(4).asText());
				checkStateModel.setDeath(mofhwData.get(i).getCell(6).asText());
				break;
			}
		}
		return checkStateModel;
	}
}
