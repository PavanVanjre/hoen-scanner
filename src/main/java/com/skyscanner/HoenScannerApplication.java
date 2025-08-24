package com.skyscanner;

import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Environment;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class HoenScannerApplication extends Application<HoenScannerConfiguration> {

    public static void main(final String[] args) throws Exception {
        new HoenScannerApplication().run(args);
    }

    @Override
    public String getName() {
        return "hoen-scanner";
    }

    @Override
    public void initialize(final Bootstrap<HoenScannerConfiguration> bootstrap) {

    }

    @Override
    public void run(HoenScannerConfiguration configuration, Environment environment) throws Exception {
        System.out.println("Welcome to Hoen Scanner!");

        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<SearchResult>> typeRef = new TypeReference<>(){};

        // Load JSON arrays from resources
        try (InputStream carsIn = Objects.requireNonNull(
                getClass().getResourceAsStream("/rental_cars.json"),
                "Missing rental_cars.json");
             InputStream hotelsIn = Objects.requireNonNull(
                     getClass().getResourceAsStream("/hotels.json"),
                     "Missing hotels.json")
        ) {
            List<SearchResult> rentalCars = mapper.readValue(carsIn, typeRef);
            List<SearchResult> hotels     = mapper.readValue(hotelsIn, typeRef);

            List<SearchResult> searchResults = new ArrayList<>(rentalCars.size() + hotels.size());
            searchResults.addAll(rentalCars);
            searchResults.addAll(hotels);

            // 6) Register resource (exposes /search)
            environment.jersey().register(new SearchResource(searchResults));
        }
    }

}