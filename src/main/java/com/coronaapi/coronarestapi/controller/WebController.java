package com.coronaapi.coronarestapi.controller;

import java.util.ArrayList;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableCell;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;


import com.coronaapi.coronarestapi.models.SampleResponse;

@RestController
public class WebController {
	
	@Scheduled(cron = "* 1 * * * *")
	@RequestMapping("/")
	public ArrayList<SampleResponse> Sample() throws Exception {
		

		
		WebClient client = new WebClient();
		client.getOptions().setCssEnabled(false);
		client.getOptions().setJavaScriptEnabled(false);

		final String url = "https://www.mohfw.gov.in/";
		HtmlPage page = client.getPage(url);

		
		final HtmlTable table = (HtmlTable) page.getByXPath("//table[@class='table table-striped table-dark']").get(9);
		
		DomNodeList<HtmlElement> cell = table.getElementsByTagName("td");

		

		
		ArrayList<SampleResponse> arrayList = new ArrayList<SampleResponse>();

		for(int i = 1 ; i<28 ; i++) {
			for(int j=1;j<2;j++) {
				arrayList.add(new SampleResponse(table.getCellAt(i, j).asText(), table.getCellAt(i, j+1).asText(), table.getCellAt(i, j+2).asText(), table.getCellAt(i, j+3).asText(), table.getCellAt(i, j+4).asText()));
			}
		}
		
		System.out.println("running....");
		return arrayList ;
	}
}
