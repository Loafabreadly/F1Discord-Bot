package com.github.loafabreadly.util.structures;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;


@Data
public class StandingsTable {
    private int season;
    @JsonProperty("StandingsLists")
    private List<StandingsList> standingsLists;
}
