package com.gloresoft.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

    @Value("#{'${configuration.countries}'.split(',')}")
    List<String> countries;

    @Override
    public List<String> getAllCountries() {
        return countries;
    }
}
