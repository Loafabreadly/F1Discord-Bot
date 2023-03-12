package com.github.loafabreadly.Util.Structures;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.net.URL;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Constructor {
    private @Getter @Setter String constructorId;
    private @Getter @Setter URL url;
    private @Getter @Setter String name;
    private @Getter @Setter String nationality;
}
