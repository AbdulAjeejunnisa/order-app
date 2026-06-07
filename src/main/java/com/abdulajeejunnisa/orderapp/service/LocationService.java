package com.abdulajeejunnisa.orderapp.service;

import com.abdulajeejunnisa.orderapp.model.Location;
import com.abdulajeejunnisa.orderapp.repository.LocationRepository;
import jakarta.transaction.Transactional;
import com.abdulajeejunnisa.orderapp.dto.UpdateLocationRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {

    private final LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public Location saveLocation(Location location) {
        return locationRepository.save(location);
    }

    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    public Location getLocationById(Long id) {
        return locationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Location Not Found"));
    }

    public Location updateLocation(Long id,
                                   Location updatedLocation) {

        Location location = getLocationById(id);

        location.setCity(updatedLocation.getCity());
        location.setState(updatedLocation.getState());
        location.setCountry(updatedLocation.getCountry());
        location.setPinCode(updatedLocation.getPinCode());

        return locationRepository.save(location);
    }
    public void deleteLocation(Long id) {
        locationRepository.deleteById(id);
    }

}