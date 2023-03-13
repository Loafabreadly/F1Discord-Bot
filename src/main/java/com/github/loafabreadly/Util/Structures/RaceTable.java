package com.github.loafabreadly.Util.Structures;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

public class RaceTable {
    private @Getter @Setter int season;
    private @Getter @Setter int round;
    @JsonProperty("Races")
    private @Getter @Setter Races[] races;
}
