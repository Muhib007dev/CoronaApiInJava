package com.coronaapi.coronarestapi.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import com.coronaapi.coronarestapi.helper.StaticHelper;
import com.coronaapi.coronarestapi.models.AllCountryModel;
import com.coronaapi.coronarestapi.models.CheckedCountryModel;
import com.coronaapi.coronarestapi.models.IndiaStateModel;
import com.coronaapi.coronarestapi.models.TotalInIndiaModel;
import com.coronaapi.coronarestapi.models.TotalWorldModel;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;

import org.springframework.stereotype.Service;

@Service
public class WebServiceImpl implements WebService {

    @Override
    public ArrayList<IndiaStateModel> getIndiaStatesData() throws Exception {
        ArrayList<IndiaStateModel> responseList = new ArrayList<>();
        List<HtmlTableRow> rows = StaticHelper.getMoHFWData();
        for (int i = 0; i < 35; i++) {
            responseList.add(new IndiaStateModel(rows.get(i).getCell(1).asText(), rows.get(i).getCell(2).asText(),
                    rows.get(i).getCell(4).asText(), rows.get(i).getCell(6).asText()));
        }
        return responseList;
    }

    @Override
    public ArrayList<AllCountryModel> allCountries() throws Exception {
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

    @Override
    public TotalWorldModel totalWorld() throws Exception {
        final HtmlTable table = StaticHelper.getWorldTable();
        TotalWorldModel model = null;
        for (int i = 8; i < 9; i++) {
            for (int j = 0; j < 1; j++) {
                model = new TotalWorldModel(table.getCellAt(i, j + 2).asText(), table.getCellAt(i, j + 4).asText(),
                        table.getCellAt(i, j + 6).asText(), table.getCellAt(i, j + 8).asText());
            }
        }
        return model;
    }

    @Override
    public TotalInIndiaModel totalInIndia() throws Exception {
        HtmlTableRow totalsRow = StaticHelper.getMoHFWData().get(35);
        TotalInIndiaModel totalInIndia = new TotalInIndiaModel();
        totalInIndia.setTotalActiveCases(totalsRow.getCell(1).asText());
        totalInIndia.setCured(totalsRow.getCell(3).asText());
        totalInIndia.setDeath(totalsRow.getCell(5).asText());

        return totalInIndia;
    }

    @Override
    public CheckedCountryModel checkCountry(String countryname) throws Exception {
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

    @Override
    public IndiaStateModel checkInState(String stateName) throws Exception {
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
