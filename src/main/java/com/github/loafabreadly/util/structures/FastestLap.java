package com.github.loafabreadly.util.structures;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FastestLap {

    private String rank;
    private String lap;

    @JsonProperty("Time")
    private F1Time time;

    @JsonProperty("AverageSpeed")
    private AverageSpeed averagespeed;
}
