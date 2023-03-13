package com.github.loafabreadly.Util.Structures;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

public class StandingsTable {
    private @Getter @Setter int season;
    @JsonProperty("StandingLists")
    private @Getter @Setter StandingsList[] standingsLists;
}
