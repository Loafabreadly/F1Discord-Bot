package com.github.loafabreadly.util.structures;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ConstructorTable {

    @JsonProperty("Constructors")
    private List<Constructor> constructorsList;
}
