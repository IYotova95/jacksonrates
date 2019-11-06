package com.spartaglobal.jacksonrates;

import com.spartaglobal.jacksonrates.HTTPServices.FixerLatestHTTPService;
import com.spartaglobal.jacksonrates.parsingJSON.RatesDeserialiser;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class RatesTests {

    private static RatesDeserialiser rates;
    private static FixerLatestHTTPService fixerLatestHTTPService = new FixerLatestHTTPService();

    @BeforeClass
    public static void setup(){
        fixerLatestHTTPService.executeLatestRatesGetRequest();
        rates = new RatesDeserialiser(fixerLatestHTTPService.getFixerLatestRatesJSONString());
    }

    @Test
    public void testSuccessIsTrue() {
        Assert.assertTrue(rates.ratesMapped.success);
    }

    @Test
    public void testDates(){
        long seconds = rates.ratesMapped.getTimestamp();
        Date date = new Date(seconds * 1000L);
        SimpleDateFormat ymd = new SimpleDateFormat("yyyy-MM-dd");
        String convertedDate = ymd.format(date);
        Assert.assertEquals(convertedDate,rates.ratesMapped.getDate());
    }

    @Test
    public void testEUR() {
        String eur = "";
        for (String key: rates.ratesMapped.getRates().keySet()) {
            if (key.equalsIgnoreCase("eur")) {
                eur = "EUR";
                break;
            }
        }
        Assert.assertEquals(rates.ratesMapped.getBase(),eur);
    }

    @Test
    public void testIfEURIsOne(){
        Map<String,Double> mapEUR = new HashMap<>();
        for (Object entry:rates.ratesMapped.getRates().entrySet()) {
            if (rates.ratesMapped.getRates().containsKey("EUR") && (rates.ratesMapped.getRates().containsValue(1.0))){
                mapEUR.put("EUR",1.0);
               break;
            }
        }
        Map<String, Double> actual = Map.of("EUR",1.0);
     Assert.assertEquals(actual,mapEUR);
    }
}
