package com.github.loafabreadly.Util.Structures;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.net.URL;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Races {
    private @Getter @Setter int season;
    private @Getter @Setter int round;
    private @Getter @Setter URL url;
    private @Getter @Setter String raceName;
    @JsonProperty("Circuit")
    private @Getter @Setter Circuit circuit;
    private @Getter @Setter String date;
    private @Getter @Setter String time;
    @JsonProperty("Results")
    private @Getter @Setter DriverResult[] driverResults;
}
