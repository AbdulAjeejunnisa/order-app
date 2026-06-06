package com.abdulajeejunnisa.orderapp.Controller;

import com.abdulajeejunnisa.orderapp.model.Driver;
import com.abdulajeejunnisa.orderapp.service.DriverService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/drivers")
@CrossOrigin("*")
public class DriverController {

    private final DriverService driverService;

    public DriverController(
            DriverService driverService) {

        this.driverService = driverService;
    }

    @PostMapping
    public Driver createDriver(
            @RequestBody Driver driver) {

        return driverService.saveDriver(driver);
    }

    @GetMapping
    public List<Driver> getAllDrivers() {

        return driverService.getAllDrivers();
    }

    @GetMapping("/{id}")
    public Driver getDriverById(
            @PathVariable Long id) {

        return driverService.getDriverById(id);
    }
}