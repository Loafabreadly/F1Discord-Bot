package com.github.loafabreadly.Util.Structures;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

public class ErgastJsonReply {
    @JsonProperty("MRData")
    private @Getter @Setter MRData mrData;
}
