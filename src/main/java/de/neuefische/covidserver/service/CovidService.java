package de.neuefische.covidserver.service;

import de.neuefische.covidserver.api.CovidApiService;
import de.neuefische.covidserver.model.ConfirmedPerDay;
import de.neuefische.covidserver.model.CovidApiCountryPerDay;
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

    public List<ConfirmedPerDay> getConfirmedCases() {
        CovidApiCountryPerDay[] covidValues = this.covidApiService.getCovidApiCountryPerDays();
        return transformArrayToArrayList(covidValues);
    }

    public ArrayList<ConfirmedPerDay> transformArrayToArrayList(CovidApiCountryPerDay[] covidValues) {
        ArrayList<ConfirmedPerDay> resultArray = new ArrayList<>();
        for (CovidApiCountryPerDay covidValue: covidValues) {
            resultArray.add(new ConfirmedPerDay(covidValue.getDate(),covidValue.getConfirmed()));
        }
        return resultArray;
    }


    public String calculateCountryConfirmedAverage() {
        arrayOfConfirmedPerDay = new ArrayList<>();
        CovidApiCountryPerDay[] covidValues = this.covidApiService.getCovidApiCountryPerDays();
        arrayOfConfirmedPerDay = transformArrayToArrayList(covidValues);

        int confirmedAverage = (arrayOfConfirmedPerDay.get(arrayOfConfirmedPerDay.size()-1).getConfirmed() - arrayOfConfirmedPerDay.get(arrayOfConfirmedPerDay.size()-8).getConfirmed())/7;

        String resultString = "The seven-day-average of new covid cases in germany is: " + confirmedAverage;

        return resultString;
    }
}
