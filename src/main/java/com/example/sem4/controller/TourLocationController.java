/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sem4.controller;

import com.example.sem4.exception.ResourceNotFoundException;
import com.example.sem4.model.Location;
import com.example.sem4.model.Tour;
import com.example.sem4.model.TourLocation;
import com.example.sem4.repository.LocationRepository;
import com.example.sem4.repository.TourLocationRepository;
import com.example.sem4.repository.TourRepository;
import com.example.sem4.util.JwtUtil;
import java.math.BigInteger;
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
public class TourLocationController {

    @Autowired
    private TourLocationRepository tourLocationRepository;

    @Autowired
    private TourRepository tourRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("admin/tour_locations")
    public List<TourLocation> getAllTourLocations() {
        return tourLocationRepository.findAll();
    }

    @GetMapping("/admin/tour_locations/{id}")
    public ResponseEntity<TourLocation> getTourLocationById(@PathVariable(name = "id") Long tourLocationId) throws ResourceNotFoundException {
        TourLocation tourLocation = tourLocationRepository.findById(tourLocationId).orElseThrow(() -> new ResourceNotFoundException("Can not found Tour Location with a given id: " + tourLocationId));
        return ResponseEntity.ok(tourLocation);
    }

    @PostMapping("admin/tour_locations/")
    public TourLocation addTourLocation(@RequestBody TourLocation tourLocation) throws ResourceNotFoundException {
        return tourLocationRepository.save(tourLocation);
    }

    @PutMapping("admin/tour_locations/{id}")
    public ResponseEntity<TourLocation> updateTourLocationById(@PathVariable(name = "id") Long tourLocationId, @RequestBody TourLocation tourLocation) throws ResourceNotFoundException {
        TourLocation currentTourLocation = tourLocationRepository.findById(tourLocationId).orElseThrow(() -> new ResourceNotFoundException("Can not found Tour Location with a given id: " + tourLocationId));
        currentTourLocation.setDate(tourLocation.getDate());
        Tour updateTour = tourRepository.findById(tourLocation.getTourId().getId()).orElseThrow(() -> new ResourceNotFoundException("Can not found Tour with a given id: " + tourLocation.getTourId().getId()));
        currentTourLocation.setTourId(updateTour);
        Location updateLocation = locationRepository.findById(tourLocation.getLocationId().getId()).orElseThrow(() -> new ResourceNotFoundException("Can not found Location with a given id: " + tourLocation.getLocationId().getId()));
        currentTourLocation.setLocationId(updateLocation);
        return ResponseEntity.ok(tourLocationRepository.save(currentTourLocation));
    }

    @DeleteMapping("admin/tour_locations/{id}")
    public Map<String, Boolean> deleteTourLocation(@PathVariable(name = "id") Long tourLocationId) throws ResourceNotFoundException {
        TourLocation currentTourLocation = tourLocationRepository.findById(tourLocationId).orElseThrow(() -> new ResourceNotFoundException("Can not found Tour Location with a given id: " + tourLocationId));
        Map<String, Boolean> response = new HashMap<>();
        for (Tour tour : tourRepository.findAll()) {
            if (tour.getId().equals(currentTourLocation.getTourId().getId())) {
                response.put("deleted", Boolean.FALSE);
                return response;
            }
        }
        tourLocationRepository.delete(currentTourLocation);
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
