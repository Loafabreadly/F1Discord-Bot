package com.github.loafabreadly.Util.Structures;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.net.URL;

public class Circuit {
    private @Getter @Setter String circuitId;
    private @Getter @Setter URL url;
    private @Getter @Setter String circuitName;
    @JsonProperty("Location")
    private @Getter @Setter Location location;
}
