package com.github.loafabreadly.Util.Structures;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

public class StandingsList {
    private @Getter @Setter int season;
    private @Getter int round;
    @JsonProperty("DriverStandings")
    private @Getter DriverStandings[] driverStandings;
    @JsonProperty("ConstructorStandings")
    private @Getter ConstructorStandings[] constructorStandings;
}
