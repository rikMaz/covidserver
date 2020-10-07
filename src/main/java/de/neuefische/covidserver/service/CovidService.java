package de.neuefische.covidserver.service;

import de.neuefische.covidserver.api.CovidApiService;
import de.neuefische.covidserver.model.ConfirmedPerDay;
import de.neuefische.covidserver.model.CovidApiCountryPerDay;
import de.neuefische.covidserver.model.CovidStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CovidService {

    private final CovidApiService covidApiService;

    private ArrayList<ConfirmedPerDay> arrayOfConfirmedPerDay;

    public CovidService(CovidApiService covidApiService) {
        this.covidApiService = covidApiService;
    }


    public ArrayList<ConfirmedPerDay> transformArrayToArrayList(CovidApiCountryPerDay[] covidValues) {
        ArrayList<ConfirmedPerDay> resultArray = new ArrayList<>();
        for (CovidApiCountryPerDay covidValue: covidValues) {
            resultArray.add(new ConfirmedPerDay(covidValue.getDate(), covidValue.getConfirmed(), covidValue.getCountry()));
        }
        return resultArray;
    }


    public CovidStatus calculateCountryConfirmedAverage(String country) {
        CovidStatus covidStatus = new CovidStatus();
        arrayOfConfirmedPerDay = new ArrayList<>();
        CovidApiCountryPerDay[] covidValues = this.covidApiService.getCovidApiCountryPerDays(country);
        arrayOfConfirmedPerDay = transformArrayToArrayList(covidValues);

        int confirmedLastDay = arrayOfConfirmedPerDay.get(arrayOfConfirmedPerDay.size() - 1).getConfirmed();
        int confirmedFirstDay = arrayOfConfirmedPerDay.get(arrayOfConfirmedPerDay.size() - 7).getConfirmed();
        int confirmedAverage = (confirmedLastDay - confirmedFirstDay)/7;

        covidStatus.setSevenDayAverageStatus(confirmedAverage);
        covidStatus.setCountry(covidValues[1].getCountry());

        return covidStatus;
    }
}
