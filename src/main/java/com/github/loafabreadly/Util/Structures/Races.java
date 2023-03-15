package com.github.loafabreadly.Util.Structures;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


import java.net.URL;
import java.util.List;

@Data
public class Races {
    @JsonProperty("season")
    private int season;

    @JsonProperty("round")
    private  int round;

    @JsonProperty("url")
    private  URL url;

    @JsonProperty("raceName")
    private  String raceName;

    @JsonProperty("Circuit")
    private  Circuit circuit;

    @JsonProperty("date")
    private  String date;

    private  String time;

    @JsonProperty("Results")
    private List<DriverResult> driverResults;
}
