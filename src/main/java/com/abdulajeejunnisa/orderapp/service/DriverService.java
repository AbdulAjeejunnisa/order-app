package com.abdulajeejunnisa.orderapp.service;

import com.abdulajeejunnisa.orderapp.model.Driver;
import com.abdulajeejunnisa.orderapp.repository.DriverRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverService {

    private final DriverRepository driverRepository;

    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    public Driver saveDriver(Driver driver) {
        return driverRepository.save(driver);
    }

    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }

    public Driver getDriverById(Long id) {
        return driverRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Driver Not Found"));
    }
}