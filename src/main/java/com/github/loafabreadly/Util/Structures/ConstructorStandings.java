package com.github.loafabreadly.Util.Structures;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

public class ConstructorStandings {
    private @Getter int position;
    private @Getter String positionText;
    private @Getter String points;
    private @Getter String wins;
    @JsonProperty("Constructor")
    private @Getter Constructor constructor;
}
