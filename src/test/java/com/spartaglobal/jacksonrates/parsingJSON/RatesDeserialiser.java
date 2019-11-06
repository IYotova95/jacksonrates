package com.spartaglobal.jacksonrates.parsingJSON;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class RatesDeserialiser {

    public RatesDTO ratesMapped;

    public RatesDeserialiser(String jsonString) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            ratesMapped = objectMapper.readValue(jsonString,RatesDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
