package com.coronaapi.coronarestapi.helper;

import java.util.List;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableBody;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;

public class StaticHelper {
	private static final String MOHFW_URL = "https://www.mohfw.gov.in/#state-data";
	private static final String WORLDOMETER_URL = "https://www.worldometers.info/coronavirus/";

	private static WebClient initializeWebClient() {

		WebClient client = new WebClient();
		client.getOptions().setCssEnabled(false);
		client.getOptions().setJavaScriptEnabled(false);

		return client;
	}

	public static List<HtmlTableRow> getMoHFWData() throws Exception {

		WebClient client = initializeWebClient();
		client.getOptions().setJavaScriptEnabled(true);
		client.getOptions().setThrowExceptionOnScriptError(false);
		HtmlPage page = client.getPage(MOHFW_URL);
		HtmlAnchor needToclick = (HtmlAnchor) page.getFirstByXPath("//*[@class='open-table']");
		needToclick.click();
		client.waitForBackgroundJavaScript(2000);
		page = (HtmlPage) client.getCurrentWindow().getEnclosedPage();
		HtmlTableBody body = (HtmlTableBody) page.getByXPath("//*[@class='statetable table table-striped']/tbody")
				.get(0);

		return body.getRows();
	}

	public static HtmlTable getWorldTable() throws Exception {

		WebClient client = initializeWebClient();
		HtmlPage page = client.getPage(WORLDOMETER_URL);

		return page.getHtmlElementById("main_table_countries_today");
	}
}