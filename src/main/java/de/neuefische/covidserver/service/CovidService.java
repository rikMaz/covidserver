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

    public CovidService(CovidApiService covidApiService) {
        this.covidApiService = covidApiService;
    }

    public List<ConfirmedPerDay> getConfirmedCases() {
        CovidApiCountryPerDay[] covidValues = this.covidApiService.getCovidApiCountryPerDays();
        return transformArrayToArrayList(covidValues);
    }

    public ArrayList<ConfirmedPerDay> transformArrayToArrayList(CovidApiCountryPerDay[] covidValues) {
        ArrayList<ConfirmedPerDay> resultValues = new ArrayList<>();
        for (CovidApiCountryPerDay covidValue: covidValues) {
            resultValues.add(new ConfirmedPerDay(covidValue.getDate(),covidValue.getConfirmed()));
        }
        return resultValues;
    }

}
