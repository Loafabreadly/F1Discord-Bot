package com.github.loafabreadly.Util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.loafabreadly.Constants;
import com.github.loafabreadly.Util.Structures.ErgastJsonReply;
import com.github.loafabreadly.Util.Structures.RaceResult;
import com.github.loafabreadly.Util.Structures.Races;
import lombok.Cleanup;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;


public class ErgastAPI {


    /**
     * The current constructor standings
     * @return A string of JSON data containing the API response
     */
    public static String getConstructorStandings() {
        String url = Constants.ERGASTAPIURL + "current/constructorStandings.json";
        try {
            return makeCall(url);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    /**
     * The current drivers standings
     * @return A string of JSON data containing the API response
     */
    public static String getDriverStandings() {
        String url = Constants.ERGASTAPIURL + "current/driverStandings.json";
        try {
            return makeCall(url);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    /**
     * Constructor specific data
     * @param constructor The Constructor/F1 Team we are interested in
     * @return A string of JSON data containing the API response
     */
    public static List<Races> getData(String constructor) {
        String url = Constants.ERGASTAPIURL + "constructors/" + constructor + "/results.json?limit=250";
        List<Races> allResults = new ArrayList<>();
        int offset = 0;
        boolean hasNextPage = true;
        while (hasNextPage) {
            try {
                ErgastObjectMapper om = new ErgastObjectMapper();
                System.out.println(url + "&offset=" + offset);
                ErgastJsonReply root = om.readValue(makeCall(url + "&offset=" + offset), ErgastJsonReply.class);
                System.out.println(root.toString());
                List<Races> results = root.getMrData().getRaceTable().getRaces();
                System.out.println("Just retrieved " + results.size() + " races");
                allResults.addAll(results);
                System.out.println("Total race count at " + allResults.size());

                int total = root.getMrData().getTotal();
                int limit = root.getMrData().getLimit();
                int count = results.size();

                if (offset + count >= total) {
                    hasNextPage = false;
                } else {
                    offset += limit;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return allResults;
    }
    /**
     * Season + Race Data
     * @param season - The season of the year we need to make a call for
     * @param raceNum - The race number we need to make a call for
     * @return A string of JSON data containing the API response
     */
    public static String getData(int season, int raceNum) {
        String url = Constants.ERGASTAPIURL + season + "/" + raceNum + "/results.json";
        try {
            return makeCall(url);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    /**
     *
     * @param url The API URl, assumes results.json is at the end.
     * @return A string of JSON data containing the API response
     */
    private static String makeCall(String url) throws Exception {
       @Cleanup CloseableHttpClient client = HttpClients.createDefault();
        HttpGet request = new HttpGet(url);

        return EntityUtils.toString(client.execute(request).getEntity());
    }
}