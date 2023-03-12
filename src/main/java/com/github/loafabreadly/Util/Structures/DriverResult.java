package com.github.loafabreadly.Util.Structures;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
@JsonIgnoreProperties(ignoreUnknown = true)
public class DriverResult {
    private @Getter @Setter int number;
    private @Getter @Setter int position;
    private @Getter @Setter String positionText;
    private @Getter @Setter int points;
    private @Getter @Setter Driver driver;
    private @Getter @Setter Constructor constructor;
    private @Getter @Setter int grid;
    private @Getter @Setter int laps;
    private @Getter @Setter String status;
}
