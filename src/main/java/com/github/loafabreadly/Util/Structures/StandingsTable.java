package com.github.loafabreadly.Util.Structures;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

public class StandingsTable {
    private @Getter @Setter int season;
    private @Getter @Setter int round;
    @JsonProperty("DriverStandings")
    private @Getter @Setter DriverStandings[] driverStandings;
}
