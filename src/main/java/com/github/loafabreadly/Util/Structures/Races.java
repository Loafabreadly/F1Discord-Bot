package com.github.loafabreadly.Util.Structures;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


import java.net.URL;
import java.util.List;

@Data
public class Races {
    private int season;
    private  int round;
    private  URL url;
    private  String raceName;
    @JsonProperty("Circuit")
    private  Circuit circuit;
    private  String date;
    private  String time;
    @JsonProperty("Results")
    private List<DriverResult> driverResults;
}
