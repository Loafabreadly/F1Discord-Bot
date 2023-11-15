package com.github.loafabreadly.util.structures;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
public class Location {

    @JsonProperty("lat")
    private  double lat;
    @JsonProperty("long")
    private  double longitude;
    private  String locality;
    private  String country;
}
