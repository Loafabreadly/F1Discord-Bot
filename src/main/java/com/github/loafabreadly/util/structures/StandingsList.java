package com.github.loafabreadly.util.structures;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class StandingsList {
    @JsonProperty("season")
    private int season;

    @JsonProperty("round")
    private int round;

    @JsonProperty("DriverStandings")
    private List<DriverStandings> driverStandings;

    @JsonProperty("ConstructorStandings")
    private List<ConstructorStandings> constructorStandings;
}
