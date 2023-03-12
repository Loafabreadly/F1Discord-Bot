package com.github.loafabreadly.Util.Structures;

import lombok.Getter;
import lombok.Setter;

import java.net.URL;
import java.util.List;

public class Races {
    private @Getter @Setter int season;
    private @Getter @Setter int round;
    private @Getter @Setter URL url;
    private @Getter @Setter String raceName;
    private @Getter @Setter Circuit circuit;
    private @Getter @Setter String date;
    private @Getter @Setter String time;
    private @Getter @Setter List<RaceResults> raceResults;
}
