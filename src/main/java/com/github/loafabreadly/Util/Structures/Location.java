package com.github.loafabreadly.Util.Structures;

import lombok.Getter;
import lombok.Setter;

public class Location {
    private @Getter @Setter double latitude;
    private @Getter @Setter double longitude;
    private @Getter @Setter String locality;
    private @Getter @Setter String country;
}
