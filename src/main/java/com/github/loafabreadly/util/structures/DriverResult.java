package com.github.loafabreadly.util.structures;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DriverResult {
    private int number;
    private int position;
    private String positionText;
    private String points;

    @JsonProperty("Driver")
    private Driver driver;

    @JsonProperty("Constructor")
    private Constructor constructor;

    private String grid;
    private int laps;
    private String status;

    @JsonProperty("Time")
    private F1Time time;

    @JsonProperty("FastestLap")
    private FastestLap fastestLap;
}
