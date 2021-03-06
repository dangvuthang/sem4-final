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
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
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
    Stream<Tour> list = tourRepository.findAll().stream();
    list = list.filter(tour -> tour.getActive() == true);
    list = list.filter(tour -> tour.getCurrentGroupSize() != tour.getMaxGroupSize());
    List<Tour> result = list.collect(Collectors.toList());
    return result;
  }

  @GetMapping("tours/{id}")
  public ResponseEntity<?> getBookingById(@PathVariable(name = "id") Integer tourId) throws ResourceNotFoundException {
    Tour tour = tourRepository.findById(tourId).orElseThrow(() -> new ResourceNotFoundException("Can not found booking with a given id: " + tourId));
    return ResponseEntity.ok(tour);
  }

  @GetMapping("tours/search")
  public List<Tour> getSeachTours(@RequestParam Optional<String> name, @RequestParam Optional<Integer> tourType, @RequestParam Optional<Integer> duration, @RequestParam Optional<Integer> guideId) {
    Stream<Tour> list = tourRepository.findAll().stream();
    if (name.isPresent()) {
      list = list.filter(tour -> tour.getName().toLowerCase().contains(name.get().toLowerCase()));
    }
    if (tourType.isPresent()) {
      list = list.filter(tour -> tour.getTourTypeId().getId().equals(tourType.get()));
    }
    if (duration.isPresent()) {
      if (duration.get() == 4) {
        list = list.filter(tour -> tour.getDuration() <= 4);
      }
      if (duration.get() == 6) {
        list = list.filter(tour -> tour.getDuration() > 4 && tour.getDuration() <= 7);
      }
      if (duration.get() == 7) {
        list = list.filter(tour -> tour.getDuration() >= 7);
      }
    }
    if (guideId.isPresent()) {
      list = list.filter(tour -> Objects.equals(tour.getGuideId().getId(), guideId.get()));
    }
    List<Tour> result = list.collect(Collectors.toList());
    return result;
  }

}
