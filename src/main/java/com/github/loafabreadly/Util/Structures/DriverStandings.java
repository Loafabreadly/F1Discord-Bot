package com.github.loafabreadly.Util.Structures;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

public class DriverStandings {
    private @Getter @Setter int position;
    private @Getter @Setter int positionText;
    private @Getter @Setter int points;
    private @Getter @Setter int wins;
    @JsonProperty("Driver")
    private @Getter @Setter Driver driver;
    @JsonProperty("Constructor")
    private @Getter @Setter Constructor[] constructor;
}
