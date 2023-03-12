package com.github.loafabreadly.Util.Structures;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Location {
    private @Getter @Setter double lat;
    @JsonProperty("long")
    private @Getter @Setter double longitude;
    private @Getter @Setter String locality;
    private @Getter @Setter String country;
}
