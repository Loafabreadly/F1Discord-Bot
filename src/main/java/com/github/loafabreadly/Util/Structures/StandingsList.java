package com.github.loafabreadly.Util.Structures;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

public class StandingsList {
    private @Getter @Setter int season;
    @JsonProperty("StandingsLists")
    private @Getter @Setter StandingsList[] standingsLists;
}
