package com.example.sem4.controller;

import com.example.sem4.model.TourType;
import com.example.sem4.repository.TourTypeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TourTypeController {

  @Autowired
  private TourTypeRepository tourTypeRepository;

  @GetMapping("tour-types")
  public List<TourType> getAllTourTypes() {
    return tourTypeRepository.findAll();
  }

}
