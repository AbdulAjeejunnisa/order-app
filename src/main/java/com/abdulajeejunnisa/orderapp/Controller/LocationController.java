package com.abdulajeejunnisa.orderapp.Controller;

import com.abdulajeejunnisa.orderapp.dto.UpdateLocationRequest;
import com.abdulajeejunnisa.orderapp.model.Location;
import com.abdulajeejunnisa.orderapp.service.LocationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/locations")
@CrossOrigin("*")
public class LocationController {

    private final LocationService locationService;

    public LocationController(
            LocationService locationService) {

        this.locationService = locationService;
    }

    @PostMapping
    public Location createLocation(
            @RequestBody Location location) {

        return locationService.saveLocation(location);
    }

    @GetMapping
    public List<Location> getAllLocations() {

        return locationService.getAllLocations();
    }

    @GetMapping("/{id}")
    public Location getLocationById(
            @PathVariable Long id) {

        return locationService.getLocationById(id);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Location> updateLocation(
            @PathVariable Long id,
            @RequestBody Location updatedLocation) {

        return ResponseEntity.ok(
                locationService.updateLocation(id, updatedLocation));
    }

}