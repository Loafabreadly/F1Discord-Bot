package com.github.loafabreadly.Util.Structures;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

public class RaceResults {
    private @Getter @Setter String wikiUrl;
    private @Getter @Setter String raceName;
    private @Getter @Setter Date date;
    private @Getter @Setter List<DriverResult> driverResults;
}