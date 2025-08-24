package com.skyscanner;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SearchResult {
    private final String city;
    private final String kind;   // e.g., "hotel" or "rental_car"
    private final String title;  // display name

    @JsonCreator
    public SearchResult(@JsonProperty("city") String city,
                        @JsonProperty("kind") String kind,
                        @JsonProperty("title") String title) {
        this.city = city;
        this.kind = kind;
        this.title = title;
    }

    @JsonProperty("city")
    public String getCity() { return city; }

    @JsonProperty("kind")
    public String getKind() { return kind; }

    @JsonProperty("title")
    public String getTitle() { return title; }
}

