package com.spartaglobal.jacksonrates;

import com.spartaglobal.jacksonrates.HTTPServices.FixerLatestHTTPService;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestObject {

    private static FixerLatestHTTPService fixerLatestHTTPService = new FixerLatestHTTPService();
    @BeforeClass
    public static void setup(){
        fixerLatestHTTPService.executeLatestRatesGetRequest();
    }

    @Test
    public void test(){
        System.out.println(fixerLatestHTTPService.getFixerLatestRatesJSONString());
    }
}
