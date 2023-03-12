package com.github.loafabreadly.Util.Structures;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
@JsonIgnoreProperties(ignoreUnknown = true)
public class F1Time {
    private @Getter @Setter long millis;
    private @Getter @Setter String time;
}
