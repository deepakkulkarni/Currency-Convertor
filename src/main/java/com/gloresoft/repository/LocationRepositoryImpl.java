package com.gloresoft.repository;

import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class LocationRepositoryImpl implements LocationRepository {

    private final static String country_API_URL = "http://country.io/names.json";

    @Override
    public List<String> getAllCountries() {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> countries = restTemplate.getForObject(country_API_URL, Map.class);
        return new ArrayList<>(countries.values());
    }
}
