package com.github.loafabreadly.Util;

import com.github.loafabreadly.Util.Structures.DriverResult;
import com.github.loafabreadly.Util.Structures.Races;

import java.util.List;

public class ErgastHandler {

    /**
     * Count the total number of points a constructor has earned
     * @param races A list of all the races the constructor has participated in
     * @return A integer total of all the points they have ever earned
     */
    public static Double getConstructorTotalPoints(List<Races> races) {
        double total = 0.0;
        for (Races r: races) {
            for (DriverResult s: r.getDriverResults()) {
                total += Double.parseDouble(s.getPoints());
                System.out.println("Total points counter - " + total);
            }
        }
        return total;
    }
}
