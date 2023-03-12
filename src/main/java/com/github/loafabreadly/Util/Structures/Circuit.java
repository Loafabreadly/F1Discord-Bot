package com.github.loafabreadly.Util.Structures;

import lombok.Getter;
import lombok.Setter;

import java.net.URL;

public class Circuit {
    private @Getter @Setter String circuitId;
    private @Getter @Setter URL url;
    private @Getter @Setter String circuitName;
    private @Getter @Setter Location location;
}
