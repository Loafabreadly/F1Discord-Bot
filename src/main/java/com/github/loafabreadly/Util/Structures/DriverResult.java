package com.github.loafabreadly.Util.Structures;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Data
public class DriverResult {
    private int number;
    private int position;
    private String positionText;
    private String points;

    @JsonProperty("Driver")
    private Driver driver;

    @JsonProperty("Constructor")
    private Constructor constructor;

    private String grid;
    private int laps;
    private String status;
}
