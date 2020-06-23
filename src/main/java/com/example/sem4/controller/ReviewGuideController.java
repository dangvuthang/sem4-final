/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sem4.controller;

import com.example.sem4.exception.ResourceNotFoundException;
import com.example.sem4.model.Guide;
import com.example.sem4.model.ReviewGuide;
import com.example.sem4.model.User;
import com.example.sem4.repository.GuideRepository;
import com.example.sem4.repository.ReviewGuideRepository;
import com.example.sem4.util.JwtUtil;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ReviewGuideController {

  @Autowired
  private ReviewGuideRepository reviewGuideRepository;

  @Autowired
  private GuideRepository guideRepository;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private JwtUtil jwtUtil;

  @GetMapping("review-guides")
  public List<ReviewGuide> getAllReviewGuides() {
    return reviewGuideRepository.findAll();
  }

  @GetMapping(value = "review-guides/{guideId}")
  public ResponseEntity<?> getAllBookingsOfUser(@PathVariable(name = "guideId") Integer guideId) {
    List<ReviewGuide> list = reviewGuideRepository.findByGuideId(new Guide(guideId));
    return ResponseEntity.ok().body(list);
  }

  @PostMapping(value = "review-guides")
  public ResponseEntity<?> updateUserPassword(@RequestBody Map<String, String> json) throws ResourceNotFoundException {
    int userId = Integer.parseInt(json.get("userId"));
    int guideId = Integer.parseInt(json.get("guideId"));
    int rating = Integer.parseInt(json.get("rating"));
    String review = json.get("review");
    ReviewGuide currentReviewGuide = new ReviewGuide();
    Timestamp now = new Timestamp(System.currentTimeMillis());
    currentReviewGuide.setCreatedAt(now);
    currentReviewGuide.setUserId(new User(userId));
    currentReviewGuide.setGuideId(new Guide(guideId));
    currentReviewGuide.setRating(rating);
    currentReviewGuide.setReview(review);
    currentReviewGuide.setActive(true);
    reviewGuideRepository.save(currentReviewGuide);
    Guide currentGuide = guideRepository.findById(guideId).orElseThrow(() -> new ResourceNotFoundException("Can not found guide with a given id " + guideId));
    BigDecimal result = (currentGuide.getRatingAverage().add(new BigDecimal(rating))).divide(new BigDecimal(2), 2);
    currentGuide.setRatingAverage(result);
    currentGuide.setNumberOfRatings(currentGuide.getNumberOfRatings() + 1);
    guideRepository.save(currentGuide);
    Map<String, String> response = new HashMap<>();
    response.put("status", "success");
    response.put("message", "Successfully saved review");
    return ResponseEntity.ok().body(response);
  }
}
