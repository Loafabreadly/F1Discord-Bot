package com.github.loafabreadly.Util.Structures;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.net.URL;

@Data
public class Constructor {
    @JsonProperty("constructorId")
    private String constructorId;
    @JsonProperty("url")
    private URL url;
    @JsonProperty("name")
    private String name;
    @JsonProperty("nationality")
    private String nationality;
}
