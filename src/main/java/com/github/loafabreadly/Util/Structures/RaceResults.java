package com.github.loafabreadly.Util.Structures;

import java.util.Date;
import java.util.List;

public class RaceResults {

    private String wikiUrl;
    private String raceName;
    private Date date;
    private List<DriverResult> driverResults;

    public String getWikiUrl() {
        return wikiUrl;
    }

    public String getRaceName() {
        return raceName;
    }

    public  Date getDate() {
        return date;
    }
    public List<DriverResult> getDriverResults() {
        return driverResults;
    }

    public void setWikiUrl(String wikiUrl) {
        this.wikiUrl = wikiUrl;
    }

    public void setRaceName(String raceName) {
        this.raceName = raceName;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDriverResults(List<DriverResult> driverResults) {
        this.driverResults = driverResults;
    }

}