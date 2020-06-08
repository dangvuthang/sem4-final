/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sem4.controller;

import com.example.sem4.exception.ResourceNotFoundException;
import com.example.sem4.model.Tour;
import com.example.sem4.repository.TourLocationRepository;
import com.example.sem4.repository.TourRepository;
import com.example.sem4.util.JwtUtil;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class TourController {

    @Autowired
    private TourRepository tourRepository;

    @Autowired
    private TourLocationRepository tourLocationRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("tours")
    public List<Tour> getAllTours() {
        return tourRepository.findAll();
    }

    @GetMapping("/admin/tours/{id}")
    public ResponseEntity<Tour> getTourById(@PathVariable(name = "id") Long tourId) throws ResourceNotFoundException {
        Tour tour = tourRepository.findById(tourId).orElseThrow(() -> new ResourceNotFoundException("Can not found Tour with a given id: " + tourId));
        System.out.println("HELLO WORLD");
        return ResponseEntity.ok(tour);
    }

    @PostMapping("admin/tours/")
    public Tour addTour(@RequestBody Tour tour) throws ResourceNotFoundException {
        return tourRepository.save(tour);
    }

    @PutMapping("admin/tours/{id}")
    public ResponseEntity<Tour> updateTourById(@PathVariable(name = "id") Long tourId, @RequestBody Tour tour) throws ResourceNotFoundException {
        Tour currentTour = tourRepository.findById(tourId).orElseThrow(() -> new ResourceNotFoundException("Can not found Role with a given id: " + tourId));
        currentTour.setDescription(tour.getDescription());
        currentTour.setDuration(tour.getDuration());
        currentTour.setEndDate(tour.getEndDate());
        currentTour.setMaxGroupSize(tour.getMaxGroupSize());
        currentTour.setName(tour.getName());
        currentTour.setStartDate(tour.getStartDate());
        currentTour.setPrice(tour.getPrice());
        currentTour.setPriceDiscount(tour.getPriceDiscount());
        currentTour.setTourImageCover(tour.getTourImageCover());
        currentTour.setActive(tour.getActive());
        return ResponseEntity.ok(tourRepository.save(currentTour));
    }

    @PutMapping("admin/tours/active/{id}")
    public ResponseEntity<Tour> activeTour(@PathVariable(name = "id") Long tourId) throws ResourceNotFoundException {
        Tour currentTour = tourRepository.findById(tourId).orElseThrow(() -> new ResourceNotFoundException("Can not found Role with a given id: " + tourId));
        if (currentTour.getActive()) {
            currentTour.setActive(false);
        }else{
            currentTour.setActive(true);
        }
        return ResponseEntity.ok(tourRepository.save(currentTour));
    }
}
