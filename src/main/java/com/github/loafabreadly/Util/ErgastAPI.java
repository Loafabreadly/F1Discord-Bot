package com.github.loafabreadly.Util;

import com.github.loafabreadly.Constants;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


public class ErgastAPI {

    public static String getData(int season, int raceNum) {
        String url = Constants.ERGASTAPIURL + season + "/" + raceNum + "/results.json";
        try {
            String reply = makeCall(url);
            return reply;
        } catch (Exception e) {
            return null;
        }
    }

    private static String makeCall(String url) throws Exception {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet request = new HttpGet(url);
        String responseJson = EntityUtils.toString(client.execute(request).getEntity());

        return responseJson;
    }
}
