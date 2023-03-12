package com.github.loafabreadly.Util.Structures;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.net.URL;

@Data
public class Constructor {
    private String constructorId;
    private URL url;
    private String name;
    private String nationality;
}
