/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sem4.controller;

import com.example.sem4.exception.ResourceNotFoundException;
import com.example.sem4.model.Tour;
import com.example.sem4.model.TourType;
import com.example.sem4.repository.TourRepository;
import com.example.sem4.repository.TourTypeRepository;
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
public class TourTypeController {
    @Autowired
    private TourTypeRepository tourTypeRepository;
    
    @Autowired
    private TourRepository tourRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("admin/tour_types")
    public List<TourType> getAllTourTypes() {
        return tourTypeRepository.findAll();
    }

    @GetMapping("/admin/tour_types/{id}")
    public ResponseEntity<TourType> getTourTypeById(@PathVariable(name = "id") Long tourTypeId) throws ResourceNotFoundException {
        TourType tourType = tourTypeRepository.findById(tourTypeId).orElseThrow(() -> new ResourceNotFoundException("Can not found Tour Type with a given id: " + tourTypeId));
        return ResponseEntity.ok(tourType);
    }
    
    @PostMapping("admin/tour_types/")
    public TourType addTourType(@RequestBody TourType tourType) throws ResourceNotFoundException {
        return tourTypeRepository.save(tourType);
    }

    @PutMapping("admin/tour_types/{id}")
    public ResponseEntity<TourType> updateTourTypeById(@PathVariable(name = "id") Long tourTypeId, @RequestBody TourType tourType) throws ResourceNotFoundException {
        TourType currentTourType= tourTypeRepository.findById(tourTypeId).orElseThrow(() -> new ResourceNotFoundException("Can not found Tour Type with a given id: " + tourTypeId));
        currentTourType.setName(tourType.getName());
        return ResponseEntity.ok(tourTypeRepository.save(currentTourType));
    }

    @DeleteMapping("admin/tour_types/{id}")
    public Map<String, Boolean> deleteTourType(@PathVariable(name = "id") Long tourTypeId) throws ResourceNotFoundException {
        TourType currentTourType = tourTypeRepository.findById(tourTypeId).orElseThrow(() -> new ResourceNotFoundException("Can not found Tour Type with a given id: " + tourTypeId));
        Map<String, Boolean> response = new HashMap<>();
        for (Tour tour : tourRepository.findAll()) {
            if (tour.getTourTypeId()==currentTourType) {
                response.put("deleted", Boolean.FALSE);
                return response;
            }
        }
        tourTypeRepository.delete(currentTourType);
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
