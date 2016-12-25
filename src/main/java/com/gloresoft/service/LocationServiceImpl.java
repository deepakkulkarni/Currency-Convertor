package com.gloresoft.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

    @Value("#{'${allCountries}'.split(',')}")
    List<String> countries = new ArrayList<>();

    @Override
    public List<String> getAllCountries() {
        return countries;
    }
}
