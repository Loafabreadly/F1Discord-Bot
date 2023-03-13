package com.github.loafabreadly.Util.Structures;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.net.URL;

public class MRData {
    private @Getter @Setter URL url;
    private @Getter @Setter String series;
    private @Getter @Setter String xmlns;
    private @Getter @Setter int limit;
    private @Getter @Setter int offset;
    private @Getter @Setter int total;
    @JsonProperty("RaceTable")
    private @Getter @Setter RaceTable raceTable;
    @JsonProperty("StandingsTable")
    private @Getter @Setter StandingsTable standingsTable;
}
