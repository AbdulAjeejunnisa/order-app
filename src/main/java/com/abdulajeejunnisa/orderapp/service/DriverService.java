package com.abdulajeejunnisa.orderapp.service;

import com.abdulajeejunnisa.orderapp.dto.UpdateDriverRequest;
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
    public List<Driver> getAvailableDrivers() {
        return driverRepository.findByAvailable(true);
    }

    public List<Driver> getUnassignedDrivers() {
        return driverRepository.findByOrdersIsEmpty();
    }

    public List<Driver> getAssignedDrivers() {
        return driverRepository.findByOrdersIsNotEmpty();
    }

    public Driver updateDriver(
            Long id,
            UpdateDriverRequest request) {

        Driver driver = driverRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Driver Not Found"));

        driver.setName(request.getName());
        driver.setEmail(request.getEmail());
        driver.setPhoneNo(request.getPhoneNo());
        driver.setVehicleNumber(request.getVehicleNumber());
        driver.setAvailable(request.isAvailable());

        return driverRepository.save(driver);
    }
    public void deleteDriver(Long id) {

        if (!driverRepository.existsById(id)) {
            throw new RuntimeException("Driver Not Found");
        }

        driverRepository.deleteById(id);
    }
}