package com.github.loafabreadly.util.structures;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


import java.net.URL;
@Data
public class MRData {
    private URL url;
    private String series;
    private String xmlns;
    private int limit;
    private int offset;
    private  int total;

    @JsonProperty("RaceTable")
    private RaceTable raceTable;

    @JsonProperty("StandingsTable")
    private StandingsTable standingsTable;

    @JsonProperty("ConstructorTable")
    private ConstructorTable constructorTable;

    @JsonProperty("CircuitTable")
    private CircuitTable circuitTable;
}
