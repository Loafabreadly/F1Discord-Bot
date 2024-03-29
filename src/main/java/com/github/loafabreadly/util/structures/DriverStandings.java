package com.github.loafabreadly.util.structures;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class DriverStandings {
    @JsonProperty("position")
    private int position;

    @JsonProperty("positionText")
    private String positionText;

    @JsonProperty("points")
    private String points;

    @JsonProperty("wins")
    private String wins;

    @JsonProperty("Driver")
    private Driver driver;

    @JsonProperty("Constructors")
    private List<Constructor> constructor;
}
