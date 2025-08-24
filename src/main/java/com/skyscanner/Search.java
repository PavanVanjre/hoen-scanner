package com.skyscanner;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Search {
    private final String city;

    @JsonCreator
    public Search(@JsonProperty("city") String city) {
        this.city = city;
    }

    @JsonProperty("city")
    public String getCity() {
        return city;
    }
}
