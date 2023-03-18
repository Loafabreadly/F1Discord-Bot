package com.github.loafabreadly.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ErgastObjectMapper extends ObjectMapper {
    public ErgastObjectMapper() {
        super();
        configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
}
