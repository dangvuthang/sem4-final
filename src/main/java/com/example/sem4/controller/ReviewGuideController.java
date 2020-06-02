/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sem4.controller;

import com.example.sem4.exception.ResourceNotFoundException;
import com.example.sem4.model.ReviewGuide;
import com.example.sem4.repository.ReviewGuideRepository;
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
public class ReviewGuideController {
    @Autowired
    private ReviewGuideRepository reviewGuideRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("admin/review_guides")
    public List<ReviewGuide> getAllReviewGuides() {
        return reviewGuideRepository.findAll();
    }

    @GetMapping("/admin/review_guides/{id}")
    public ResponseEntity<ReviewGuide> getReviewGuideById(@PathVariable(name = "id") Long reviewGuideId) throws ResourceNotFoundException {
        ReviewGuide reviewGuide = reviewGuideRepository.findById(reviewGuideId).orElseThrow(() -> new ResourceNotFoundException("Can not found Review Guide with a given id: " + reviewGuideId));
        return ResponseEntity.ok(reviewGuide);
    }
}
