package com.github.loafabreadly.Util.Structures;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

public class DriverStandings {
    private @Getter @Setter int position;
    private @Getter @Setter String positionText;
    private @Getter @Setter String points;
    private @Getter @Setter String wins;
    @JsonProperty("Driver")
    private @Getter @Setter Driver driver;
    @JsonProperty("Constructor")
    private @Getter @Setter Constructor[] constructor;
}
