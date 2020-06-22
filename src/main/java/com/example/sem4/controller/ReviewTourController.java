/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sem4.controller;

import com.example.sem4.exception.ResourceNotFoundException;
import com.example.sem4.model.ReviewTour;
import com.example.sem4.model.Tour;
import com.example.sem4.model.User;
import com.example.sem4.repository.ReviewTourRepository;
import com.example.sem4.repository.TourRepository;
import com.example.sem4.util.JwtUtil;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ReviewTourController {

  @Autowired
  private ReviewTourRepository reviewTourRepository;

  @Autowired
  private TourRepository tourRepository;

  @Autowired
  private JwtUtil jwtUtil;

  @GetMapping("review-tours")
  public List<ReviewTour> getAllReviewTours() {
    return reviewTourRepository.findAll();
  }

  @PostMapping(value = "review-tours")
  public ResponseEntity<?> updateUserPassword(@RequestBody Map<String, String> json) throws ResourceNotFoundException {
    int userId = Integer.parseInt(json.get("userId"));
    int tourId = Integer.parseInt(json.get("tourId"));
    int rating = Integer.parseInt(json.get("rating"));
    String review = json.get("review");
    ReviewTour currentReview = new ReviewTour();
    Timestamp now = new Timestamp(System.currentTimeMillis());
    currentReview.setCreatedAt(now);
    currentReview.setTourId(new Tour(tourId));
    currentReview.setUserId(new User(userId));
    currentReview.setRating(rating);
    currentReview.setReview(review);
    currentReview.setActive(true);
    reviewTourRepository.save(currentReview);
    Tour currentTour = tourRepository.findById(tourId).orElseThrow(() -> new ResourceNotFoundException("Can not found tour with a given id " + tourId));
    BigDecimal result = (currentTour.getRatingAverage().add(new BigDecimal(rating))).divide(new BigDecimal(2), 2);
    currentTour.setRatingAverage(result);
    currentTour.setNumberOfRatings(currentTour.getNumberOfRatings().add(BigInteger.ONE));
    tourRepository.save(currentTour);
    Map<String, String> response = new HashMap<>();
    response.put("status", "success");
    response.put("message", "Successfully saved review");
    return ResponseEntity.ok().body(response);
  }
}
