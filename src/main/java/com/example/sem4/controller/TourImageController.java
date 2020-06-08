/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sem4.controller;

import com.example.sem4.exception.ResourceNotFoundException;
import com.example.sem4.model.Tour;
import com.example.sem4.model.TourImage;
import com.example.sem4.repository.TourImageRepository;
import com.example.sem4.repository.TourRepository;
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
public class TourImageController {
    @Autowired
    private TourImageRepository tourImageRepository;
    
    @Autowired
    private TourRepository tourRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("admin/tour_images")
    public List<TourImage> getAllTourImages() {
        return tourImageRepository.findAll();
    }

    @GetMapping("/admin/tour_images/{id}")
    public ResponseEntity<TourImage> getTourImageById(@PathVariable(name = "id") Integer tourImageId) throws ResourceNotFoundException {
        TourImage tourImage = tourImageRepository.findById(tourImageId).orElseThrow(() -> new ResourceNotFoundException("Can not found Tour Image with a given id: " + tourImageId));
        return ResponseEntity.ok(tourImage);
    }
    
    @PostMapping("admin/tour_images/")
    public TourImage addTourImage(@RequestBody TourImage tourImage) throws ResourceNotFoundException {
        return tourImageRepository.save(tourImage);
    }

    @PutMapping("admin/tour_images/{id}")
    public ResponseEntity<TourImage> updateTourImageById(@PathVariable(name = "id") Integer tourImageId, @RequestBody TourImage tourImage) throws ResourceNotFoundException {
        TourImage currentTourImage= tourImageRepository.findById(tourImageId).orElseThrow(() -> new ResourceNotFoundException("Can not found TourImage with a given id: " + tourImageId));
        currentTourImage.setImageUrl(tourImage.getImageUrl());
        return ResponseEntity.ok(tourImageRepository.save(currentTourImage));
    }

    @DeleteMapping("admin/tour_images/{id}")
    public Map<String, Boolean> deleteTourImage(@PathVariable(name = "id") Integer roleId) throws ResourceNotFoundException {
        TourImage currentTourImage = tourImageRepository.findById(roleId).orElseThrow(() -> new ResourceNotFoundException("Can not found TourImage with a given id: " + roleId));
        Map<String, Boolean> response = new HashMap<>();
        for (Tour tour : tourRepository.findAll()) {
            if (tour.getId().equals(currentTourImage.getTourId().getId())) {
                response.put("deleted", Boolean.FALSE);
                return response;
            }
        }
        tourImageRepository.delete(currentTourImage);
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
