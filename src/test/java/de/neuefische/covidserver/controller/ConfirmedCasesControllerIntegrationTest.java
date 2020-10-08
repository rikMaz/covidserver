package de.neuefische.covidserver.controller;

import de.neuefische.covidserver.api.CovidApiService;
import de.neuefische.covidserver.model.ConfirmedPerDay;
import de.neuefische.covidserver.model.CovidApiCountryPerDay;
import de.neuefische.covidserver.model.CovidStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ConfirmedCasesControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @MockBean
    private CovidApiService covidApiService;

    @Test
    public void getCovidConfirmedGermany(){
        //GIVEN
        String url = "http://localhost:" +port+"/covid/confirmed/";
        String country = "germany";
        CovidApiCountryPerDay[] values = {
                new CovidApiCountryPerDay("2020-04-04 20:50:31 +0000 UTC", 10, "germany"),
                new CovidApiCountryPerDay("2020-04-06 20:50:31 +0000 UTC", 30, "germany"),
                new CovidApiCountryPerDay("2020-04-07 20:50:31 +0000 UTC", 45, "germany"),
                new CovidApiCountryPerDay("2020-04-08 20:50:31 +0000 UTC", 339, "germany"),
                new CovidApiCountryPerDay("2020-04-09 20:50:31 +0000 UTC", 424, "germany"),
                new CovidApiCountryPerDay("2020-04-10 20:50:31 +0000 UTC", 560, "germany"),
                new CovidApiCountryPerDay("2020-04-11 20:50:31 +0000 UTC", 600, "germany")
        };
        when(covidApiService.getCovidApiCountryPerDays(country)).thenReturn(values);

        //WHEN
        ResponseEntity<CovidStatus> response = testRestTemplate.getForEntity(url + country, CovidStatus.class);

        //THEN
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), is(new CovidStatus(84, "germany")));



    }
}