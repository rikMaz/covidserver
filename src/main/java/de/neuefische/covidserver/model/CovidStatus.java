package de.neuefische.covidserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CovidStatus {

    private int sevenDayAverageStatus;
    private String country;

}
