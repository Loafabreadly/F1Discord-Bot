package com.github.loafabreadly.Util.Structures;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class RaceTable {
    private int season;
    private int round;

    @JsonProperty("constructorId")
    private String constructorId;

    @JsonProperty("Races")
    private List<Races> races;
}
