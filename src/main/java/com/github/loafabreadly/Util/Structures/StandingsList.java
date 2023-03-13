package com.github.loafabreadly.Util.Structures;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class StandingsList {
    @JsonProperty("season")
    private int season;

    @JsonProperty("round")
    private int round;

    @JsonProperty("DriverStandings")
    private DriverStandings[] driverStandings;

    @JsonProperty("ConstructorStandings")
    private  ConstructorStandings[] constructorStandings;
}
