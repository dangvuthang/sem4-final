/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sem4.controller;

import com.example.sem4.exception.ResourceNotFoundException;
import com.example.sem4.model.ReviewTour;
import com.example.sem4.repository.ReviewTourRepository;
import com.example.sem4.util.JwtUtil;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class ReviewTourController {
    @Autowired
    private ReviewTourRepository reviewTourRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("admin/review_tours")
    public List<ReviewTour> getAllReviewTours() {
        return reviewTourRepository.findAll();
    }

    @GetMapping("/admin/review_tours/{id}")
    public ResponseEntity<ReviewTour> getReviewTourById(@PathVariable(name = "id") Long reviewTourId) throws ResourceNotFoundException {
        ReviewTour reviewTour = reviewTourRepository.findById(reviewTourId).orElseThrow(() -> new ResourceNotFoundException("Can not found Review Tour with a given id: " + reviewTourId));
        return ResponseEntity.ok(reviewTour);
    }
}
