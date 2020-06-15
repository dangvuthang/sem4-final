/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sem4.controller;

import com.example.sem4.exception.ResourceNotFoundException;
import com.example.sem4.model.Tour;
import com.example.sem4.model.TourType;
import com.example.sem4.repository.TourLocationRepository;
import com.example.sem4.repository.TourRepository;
import com.example.sem4.util.JwtUtil;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
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

    return tourRepository.findAll();
  }

  @GetMapping("tours/search")
  public List<Tour> getSeachTours(@RequestParam Optional<String> name, @RequestParam Optional<Integer> tourType, @RequestParam Optional<Integer> duration) {
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
    List<Tour> result = list.collect(Collectors.toList());
    return result;
  }

}
