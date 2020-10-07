package de.neuefische.covidserver.controller;

import de.neuefische.covidserver.model.ConfirmedPerDay;
import de.neuefische.covidserver.model.CovidStatus;
import de.neuefische.covidserver.service.CovidService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("covid/confirmed")
public class ConfirmedCasesController {

    private final CovidService covidService;

    public ConfirmedCasesController(CovidService covidService) {
        this.covidService = covidService;
    }

    @GetMapping("{country}")
    public CovidStatus getCountryConfirmedAvarage(@PathVariable String country) {
        return covidService.calculateCountryConfirmedAverage(country);
    }


}
