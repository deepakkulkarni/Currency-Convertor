package com.gloresoft.service;

import com.gloresoft.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationRepository locationRepository;

    @Override
    public List<String> getAllCountries() {
        return locationRepository.getAllCountries();
    }
}
