package com.coronaapi.coronarestapi.controller;

import com.coronaapi.coronarestapi.models.*;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableBody;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;
import org.apache.commons.lang3.StringUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class WebController {

	public static final String MOHFW_URL = "https://www.mohfw.gov.in/#state-data";
	public static final String WORLDOMETER_URL = "https://www.worldometers.info/coronavirus/";

	@Scheduled(cron = "* 1 * * * *")
	@GetMapping("/india-states-data")
	public ArrayList<IndiaStateModel> getIndiaStatesData() throws Exception {
		List<HtmlTableRow> rows = getMoHFWData();
		ArrayList<IndiaStateModel> responseList = new ArrayList<>();
		for (int i = 0; i < 35; i++) {
			responseList.add(new IndiaStateModel(rows.get(i).getCell(1).asText(), rows.get(i).getCell(2).asText(),
					rows.get(i).getCell(4).asText(), rows.get(i).getCell(6).asText()));
		}
		return responseList;
	}

	private WebClient initializeWebClient() {
		WebClient client = new WebClient();
		client.getOptions().setCssEnabled(false);
		client.getOptions().setJavaScriptEnabled(false);
		return client;
	}

	@Scheduled(cron = "* 1 * * * *")
	@RequestMapping("/all-countries")
	public ArrayList<AllCountryModel> all() throws Exception {

		WebClient client = initializeWebClient();
		HtmlPage page = client.getPage(WORLDOMETER_URL);
		final HtmlTable table = page.getHtmlElementById("main_table_countries_today");

		ArrayList<AllCountryModel> arrayList = new ArrayList<>();

		 for(int i = 9 ; i<224 ; i++) {
		for(int j=0;j<1;j++) {
				arrayList.add(new AllCountryModel(table.getCellAt(i, j+1).asText(), table.getCellAt(i, j + 2).asText(),
						table.getCellAt(i, j + 4).asText(), table.getCellAt(i, j + 6).asText(),
						table.getCellAt(i, j + 8).asText()));
			}
		}
//			System.out.println(table.getCellAt(i, j).asText()+table.getCellAt(i, j+1).asText()+" "+table.getCellAt(i, j+3).asText()+" "+table.getCellAt(i, j+5).asText()+" "+table.getCellAt(i, j+6).asText());

		return arrayList;
	}

	@Scheduled(cron = "* 1 * * * *")
	@RequestMapping("/total-world")
	public TotalModel total() throws Exception {

		WebClient client = initializeWebClient();
		HtmlPage page = client.getPage(WORLDOMETER_URL);
		final HtmlTable table = page.getHtmlElementById("main_table_countries_today");

		TotalModel model = null;

		for (int i = 8; i < 9; i++) {
			for (int j = 0; j < 1; j++) {
				model = new TotalModel(table.getCellAt(i, j + 2).asText(), table.getCellAt(i, j + 4).asText(),
						table.getCellAt(i, j + 6).asText(), table.getCellAt(i, j + 8).asText());
			}
		}
//			System.out.println(table.getCellAt(i, j).asText()+table.getCellAt(i, j+1).asText()+" "+table.getCellAt(i, j+3).asText()+" "+table.getCellAt(i, j+5).asText()+" "+table.getCellAt(i, j+6).asText());

		return model;
	}

	@Scheduled(cron = "* 1 * * * *")
	@GetMapping("/total-in-india")
	public TotalInIndiaModel totalInIndia() throws Exception {
		HtmlTableRow totalsRow = getMoHFWData().get(35);

		TotalInIndiaModel totalInIndia = new TotalInIndiaModel();
			totalInIndia.setTotalActiveCases(totalsRow.getCell(1).asText());
			totalInIndia.setCured(totalsRow.getCell(3).asText());
			totalInIndia.setDeath(totalsRow.getCell(5).asText());

		return totalInIndia;
	}

	@RequestMapping("/country")
	public CheckedCountryModel checkCountry(
			@RequestParam(value = "countryname", defaultValue = "India") String countryname) throws Exception {

		WebClient client = initializeWebClient();
		HtmlPage page = client.getPage(WORLDOMETER_URL);
		final HtmlTable table = page.getHtmlElementById("main_table_countries_today");

		CheckedCountryModel countryModel = null;

		 for(int i = 9 ; i<224 ; i++) {
		for(int j=0;j<1;j++) {
				if (StringUtils.containsIgnoreCase(countryname, table.getCellAt(i, j+1).asText())) {
					countryModel = new CheckedCountryModel(table.getCellAt(i, j+1).asText(),
							table.getCellAt(i, j + 2).asText(), table.getCellAt(i, j + 4).asText(),
							table.getCellAt(i, j + 6).asText(), table.getCellAt(i, j + 8).asText());
				}

			}
		}
//			System.out.println(table.getCellAt(i, j).asText()+table.getCellAt(i, j+1).asText()+" "+table.getCellAt(i, j+3).asText()+" "+table.getCellAt(i, j+5).asText()+" "+table.getCellAt(i, j+6).asText());

		return countryModel;

	}

	@RequestMapping("/state")
	public IndiaStateModel checkInState(
			@RequestParam(value = "statename", defaultValue = "Maharashtra") String stateName) throws Exception {

		List<HtmlTableRow> mofhwData = getMoHFWData();
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


	private List<HtmlTableRow> getMoHFWData() throws Exception {
		WebClient client = initializeWebClient();
		client.getOptions().setJavaScriptEnabled(true);
		client.getOptions().setThrowExceptionOnScriptError(false);
		HtmlPage page = client.getPage(MOHFW_URL);
		HtmlAnchor needToclick = (HtmlAnchor) page.getFirstByXPath("//*[@class='open-table']");
		needToclick.click();
		client.waitForBackgroundJavaScript(2000);
		page = (HtmlPage) client.getCurrentWindow().getEnclosedPage();
		HtmlTableBody body = (HtmlTableBody) page.getByXPath("//*[@class='statetable table table-striped']/tbody").get(0);
		return body.getRows();
	}
}


//@RequestMapping("/country")
//public ArrayList<CheckedCountryModel> checkCountry(@RequestParam(value = "countryname",
//		defaultValue = "Please Check The Parameter") String countryname) throws Exception{
//
//	WebClient client = new WebClient();
//	client.getOptions().setCssEnabled(false);
//	client.getOptions().setJavaScriptEnabled(false);
//
//	final String url = "https://www.worldometers.info/coronavirus/";
//	HtmlPage page = client.getPage(url);
//	final HtmlTable table = page.getHtmlElementById("main_table_countries_today");
//
//	ArrayList<CheckedCountryModel> arrayList = new ArrayList<CheckedCountryModel>();
//
//
//
////	CheckedCountryModel countryModel = new CheckedCountryModel(countryname, countryname, countryname, countryname, countryname);
//
//
//
//	 for(int i = 1 ; i<203 ; i++) {
//	for(int j=0;j<1;j++) {
//		if(countryname.contains(table.getCellAt(i, j).asText())) {
//			arrayList.add(new CheckedCountryModel(table.getCellAt(i, j).asText(), table.getCellAt(i, j+1).asText(), table.getCellAt(i, j+3).asText(), table.getCellAt(i, j+5).asText(), table.getCellAt(i, j+6).asText()));
//		}
//
//	}
//}
////		System.out.println(table.getCellAt(i, j).asText()+table.getCellAt(i, j+1).asText()+" "+table.getCellAt(i, j+3).asText()+" "+table.getCellAt(i, j+5).asText()+" "+table.getCellAt(i, j+6).asText());
//
//			return arrayList;
//
//}
