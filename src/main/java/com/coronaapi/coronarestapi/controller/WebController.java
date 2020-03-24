package com.coronaapi.coronarestapi.controller;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Dictionary;
import java.util.Hashtable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableCell;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;


import com.coronaapi.coronarestapi.models.SampleResponse;

@RestController
public class WebController {
	@RequestMapping("/sample")
	public SampleResponse Sample(@RequestParam(value = "name",
	defaultValue = "Robot") String name) throws Exception {
		
		
		WebClient client = new WebClient();
		client.getOptions().setCssEnabled(false);
		client.getOptions().setJavaScriptEnabled(false);

		final String url = "https://www.mohfw.gov.in/";
		HtmlPage page = client.getPage(url);

//    	final HtmlTable table = page.getHtmlElementById("table table-striped table-dark");

		final HtmlTable table = (HtmlTable) page.getByXPath("//table[@class='table table-striped table-dark']").get(1);

		Dictionary dictionary = new Hashtable();
		int i=0;
		for (final HtmlTableRow row : table.getRows()) {

			if(row.asText().contains("S. No.")) {
    	    System.out.println(row.asText());
		}
		else if(row.asText().contains("Name of State")) {
			System.out.println(row.asText());
		}
		else if(row.asText().contains("Total Confirmed cases (Indian National)")) {
			System.out.println(row.asText());
		}
		i++;
		System.out.println(row.asText());


//			for (final HtmlTableCell cell : row.getCells()) {
//				if(cell.asText().contains("S. No.")) {
//		    	    System.out.println(cell.asText());
//				}
//				else if(cell.asText().contains("Name of State")) {
//					System.out.println(cell.asText());
//				}
//				else if(cell.asText().contains("Total Confirmed cases (Indian National)")) {
//					System.out.println(cell.asText());
//				}
//				i++;
//				System.out.println(i);
//			}

		}		
		
		SampleResponse response = new SampleResponse();
		response.setId(1);
		response.setMessage("Your name is "+name);
		return response;
	}
}
