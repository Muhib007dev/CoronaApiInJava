package com.coronaapi.coronarestapi.controller;

import com.coronaapi.coronarestapi.models.*;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.stream.IntStream;

@RestController
public class WebController {

	public static final String MOHFW_URL = "https://www.mohfw.gov.in/";
	public static final String WORLDOMETER_URL = "https://www.worldometers.info/coronavirus/";

	@Scheduled(cron = "* 1 * * * *")
	@RequestMapping("/india-states-data")
	public ArrayList<SampleResponse> Sample() throws Exception {

		WebClient client = initializeWebClient();
		HtmlPage page = client.getPage(MOHFW_URL);

		final HtmlTable table = (HtmlTable) page.getByXPath("//table[@class='table table-striped']").get(0);

		ArrayList<SampleResponse> arrayList = new ArrayList<>();

		for (int i = 1; i < 32; i++) {
			for (int j = 1; j < 2; j++) {
				arrayList.add(new SampleResponse(table.getCellAt(i, j).asText(), table.getCellAt(i, j + 1).asText(),
						table.getCellAt(i, j + 2).asText(), table.getCellAt(i, j + 3).asText()));
			}
		}

		System.out.println("running....");
		return arrayList;
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

		 for(int i = 9 ; i<220 ; i++) {
		for(int j=0;j<1;j++) {
				arrayList.add(new AllCountryModel(table.getCellAt(i, j).asText(), table.getCellAt(i, j + 1).asText(),
						table.getCellAt(i, j + 3).asText(), table.getCellAt(i, j + 5).asText(),
						table.getCellAt(i, j + 6).asText()));
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
				model = new TotalModel(table.getCellAt(i, j + 1).asText(), table.getCellAt(i, j + 3).asText(),
						table.getCellAt(i, j + 5).asText(), table.getCellAt(i, j + 6).asText());
			}
		}
//			System.out.println(table.getCellAt(i, j).asText()+table.getCellAt(i, j+1).asText()+" "+table.getCellAt(i, j+3).asText()+" "+table.getCellAt(i, j+5).asText()+" "+table.getCellAt(i, j+6).asText());

		return model;
	}

	@Scheduled(cron = "* 1 * * * *")
	@RequestMapping("/total-in-india")
	public TotalInIndiaModel totalInIndia() throws Exception {

		WebClient client = initializeWebClient();
		HtmlPage page = client.getPage(MOHFW_URL);

		final HtmlTable table = (HtmlTable) page.getByXPath("//table[@class='table table-striped']").get(0);

		TotalInIndiaModel totalInIndia = new TotalInIndiaModel();
		TotalInIndiaModel finalTotalInIndia = totalInIndia;
		IntStream.range(32, 33).forEach(index -> {
			finalTotalInIndia.setTotalConfirmedCases(table.getCellAt(index, 2).asText());
			finalTotalInIndia.setCured(table.getCellAt(index, 3).asText());
			finalTotalInIndia.setDeath(table.getCellAt(index, 4).asText());
		});

		return totalInIndia;
	}

	@RequestMapping("/country")
	public CheckedCountryModel checkCountry(
			@RequestParam(value = "countryname", defaultValue = "India") String countryname) throws Exception {

		WebClient client = initializeWebClient();
		HtmlPage page = client.getPage(WORLDOMETER_URL);
		final HtmlTable table = page.getHtmlElementById("main_table_countries_today");

		CheckedCountryModel countryModel = null;

		 for(int i = 9 ; i<220 ; i++) {
		for(int j=0;j<1;j++) {
				if (StringUtils.containsIgnoreCase(countryname, table.getCellAt(i, j).asText())) {
					countryModel = new CheckedCountryModel(table.getCellAt(i, j).asText(),
							table.getCellAt(i, j + 1).asText(), table.getCellAt(i, j + 3).asText(),
							table.getCellAt(i, j + 5).asText(), table.getCellAt(i, j + 6).asText());
				}

			}
		}
//			System.out.println(table.getCellAt(i, j).asText()+table.getCellAt(i, j+1).asText()+" "+table.getCellAt(i, j+3).asText()+" "+table.getCellAt(i, j+5).asText()+" "+table.getCellAt(i, j+6).asText());

		return countryModel;

	}

	@RequestMapping("/state")
	public CheckStateModel checkInState(
			@RequestParam(value = "statename", defaultValue = "Maharashtra") String stateName) throws Exception {

		WebClient client = initializeWebClient();
		HtmlPage page = client.getPage(MOHFW_URL);

		final HtmlTable table = (HtmlTable) page.getByXPath("//table[@class='table table-striped']").get(0);

		CheckStateModel checkStateModel = new CheckStateModel();
		IntStream.range(1, 32).forEach(index -> {
			if (StringUtils.containsIgnoreCase(stateName, table.getCellAt(index, 1).asText())) {
				checkStateModel.setState(stateName);
				checkStateModel.setTotalConfirmedCases(table.getCellAt(index, 2).asText());
				checkStateModel.setCured(table.getCellAt(index, 3).asText());
				checkStateModel.setDeath(table.getCellAt(index, 4).asText());
			}
		});

		System.out.println("running....");
		return checkStateModel;
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