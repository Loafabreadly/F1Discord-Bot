package com.github.loafabreadly.util.structures;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class CircuitTable {

    @JsonProperty("Circuits")
    private List<Circuit> circuitsList;
}
