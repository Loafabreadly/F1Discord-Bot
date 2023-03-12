package com.github.loafabreadly.Util.Structures;

import lombok.Getter;
import lombok.Setter;

import java.net.URL;
import java.util.List;

public class RaceTable {
    private @Getter @Setter int season;
    private @Getter @Setter int round;
    private @Getter @Setter List<Races> races;
}
