package de.neuefische.covidserver.api;

import de.neuefische.covidserver.model.ConfirmedPerDay;
import de.neuefische.covidserver.model.CovidApiCountryPerDay;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CovidApiService {

    private final static String apiUrl = "https://api.covid19api.com/country/";
    private final RestTemplate restTemplate = new RestTemplate();


    public CovidApiCountryPerDay[] getCovidApiCountryPerDays(String country) {
        ResponseEntity<CovidApiCountryPerDay[]> response = restTemplate.getForEntity(apiUrl + country, CovidApiCountryPerDay[].class);
        return response.getBody();
    }
}
