package com.github.loafabreadly.util.structures;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ConstructorStandings {
    @JsonProperty("position")
    private  int position;

    @JsonProperty("positionText")
    private String positionText;

    @JsonProperty("points")
    private  String points;

    @JsonProperty("wins")
    private String wins;

    @JsonProperty("Constructor")
    private Constructor constructor;
}
