package com.github.loafabreadly.Util;

import com.github.loafabreadly.Constants;
import lombok.Cleanup;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


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
    public static String getData(String constructor) {
        String url = Constants.ERGASTAPIURL + "constructors/" + constructor;
        try {
            return makeCall(url);
        } catch (Exception e) {
            return e.getMessage();
        }
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