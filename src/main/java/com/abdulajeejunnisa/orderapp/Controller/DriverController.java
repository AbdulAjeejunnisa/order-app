package com.abdulajeejunnisa.orderapp.Controller;

import com.abdulajeejunnisa.orderapp.dto.UpdateDriverRequest;
import com.abdulajeejunnisa.orderapp.model.Driver;
import com.abdulajeejunnisa.orderapp.service.DriverService;
import org.springframework.http.ResponseEntity;
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
    @PutMapping("/{id}")
    public Driver updateDriver(
            @PathVariable Long id,
            @RequestBody UpdateDriverRequest request) {

        return driverService.updateDriver(id, request);
    }
    @DeleteMapping("/{id}")
    public String deleteDriver(@PathVariable Long id) {

        driverService.deleteDriver(id);

        return "Driver Deleted Successfully";
    }
    @GetMapping("/available")
    public List<Driver> getAvailableDrivers() {

        return driverService.getAvailableDrivers();
    }
    @GetMapping("/unassigned")
    public ResponseEntity<List<Driver>> getUnassignedDrivers() {
        return ResponseEntity.ok(driverService.getUnassignedDrivers());
    }

    @GetMapping("/assigned")
    public ResponseEntity<List<Driver>> getAssignedDrivers() {
        return ResponseEntity.ok(driverService.getAssignedDrivers());
    }
}