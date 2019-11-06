package com.spartaglobal.jacksonrates.HTTPServices;

import com.spartaglobal.jacksonrates.config.APIKey;
import com.spartaglobal.jacksonrates.config.FixerURLConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class FixerLatestHTTPService {

    private CloseableHttpResponse fixerResponse;
    private String fixerLatestRatesJSONString;

    public void executeLatestRatesGetRequest(){
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();

        String latestServiceURL = FixerURLConfig.BASEURL + FixerURLConfig.LATESTRATESENDPOINT + "?" + APIKey.APIKEY;

        HttpGet httpGet = new HttpGet(latestServiceURL);
        try {
            fixerResponse = closeableHttpClient.execute(httpGet);
            fixerLatestRatesJSONString = EntityUtils.toString(fixerResponse.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getFixerLatestRatesJSONString() {
        return fixerLatestRatesJSONString;
    }

    public CloseableHttpResponse getFixerResponse() {
        return fixerResponse;
    }
}
