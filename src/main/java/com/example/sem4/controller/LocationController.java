/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sem4.controller;

import com.example.sem4.exception.ResourceNotFoundException;
import com.example.sem4.model.Location;
import com.example.sem4.model.Role;
import com.example.sem4.model.Tour;
import com.example.sem4.model.TourLocation;
import com.example.sem4.repository.LocationRepository;
import com.example.sem4.repository.TourLocationRepository;
import com.example.sem4.util.JwtUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class LocationController {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private TourLocationRepository tourLocationRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("admin/locations")
    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    @GetMapping("/admin/locations/{id}")
    public ResponseEntity<Location> getLocationById(@PathVariable(name = "id") Long locationId) throws ResourceNotFoundException {
        Location location = locationRepository.findById(locationId).orElseThrow(() -> new ResourceNotFoundException("Can not found Location with a given id: " + locationId));
        return ResponseEntity.ok(location);
    }

    @PostMapping("admin/locations/")
    public Location addLocation(@RequestBody Location location) throws ResourceNotFoundException {
        return locationRepository.save(location);
    }

    @PutMapping("admin/locations/{id}")
    public ResponseEntity<Location> updateLocationById(@PathVariable(name = "id") Long locationId, @RequestBody Location location) throws ResourceNotFoundException {
        Location currentLocation = locationRepository.findById(locationId).orElseThrow(() -> new ResourceNotFoundException("Can not found Location with a given id: " + locationId));
        currentLocation.setName(location.getName());
        currentLocation.setLongitude(location.getLongitude());
        currentLocation.setLatitude(location.getLatitude());
        currentLocation.setAddress(location.getAddress());
        return ResponseEntity.ok(locationRepository.save(currentLocation));
    }

    @DeleteMapping("admin/locations/{id}")
    public Map<String, Boolean> deleteLocation(@PathVariable(name = "id") Long locationId) throws ResourceNotFoundException {
        Location currentLocation = locationRepository.findById(locationId).orElseThrow(() -> new ResourceNotFoundException("Can not found Location with a given id: " + locationId));
        Map<String, Boolean> response = new HashMap<>();
        for (TourLocation tourLocation : tourLocationRepository.findAll()) {
            if (tourLocation.getLocationId() == currentLocation) {
                response.put("deleted", Boolean.FALSE);
                return response;
            }
        }
        locationRepository.delete(currentLocation);
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
