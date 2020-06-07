/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sem4.controller;

import com.example.sem4.exception.ResourceNotFoundException;
import com.example.sem4.model.Guide;
import com.example.sem4.model.User;
import com.example.sem4.repository.GuideRepository;
import com.example.sem4.repository.UserRepository;
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
public class GuideController {
    @Autowired
    private GuideRepository guideRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/guides")
    public List<Guide> getAllGuides() {
        return guideRepository.findAll();
    }

    @GetMapping("/admin/guides/{id}")
    public ResponseEntity<Guide> getGuideById(@PathVariable(name = "id") Long guideId) throws ResourceNotFoundException {
        Guide guide = guideRepository.findById(guideId).orElseThrow(() -> new ResourceNotFoundException("Can not found Guide with a given id: " + guideId));
        return ResponseEntity.ok(guide);
    }
    
    @PostMapping("admin/guides/")
    public Guide addGuide(@RequestBody Guide guide) throws ResourceNotFoundException {
        return guideRepository.save(guide);
    }

    @PutMapping("admin/guides/{id}")
    public ResponseEntity<Guide> updateGuideById(@PathVariable(name = "id") Long guideId, @RequestBody Guide guide) throws ResourceNotFoundException {
        Guide currentGuide = guideRepository.findById(guideId).orElseThrow(() -> new ResourceNotFoundException("Can not found Guide with a given id: " + guideId));
        currentGuide.setDescription(guide.getDescription());
        return ResponseEntity.ok(guideRepository.save(currentGuide));
    }

    @DeleteMapping("admin/guides/{id}")
    public Map<String, Boolean> deleteGuide(@PathVariable(name = "id") Long guideId) throws ResourceNotFoundException {
        Guide currentGuide = guideRepository.findById(guideId).orElseThrow(() -> new ResourceNotFoundException("Can not found Guide with a given id: " + guideId));
        Map<String, Boolean> response = new HashMap<>();
        for (User user : userRepository.findAll()) {
            if (user.getGuideCollection().contains(currentGuide)) {
                response.put("deleted", Boolean.FALSE);
                return response;
            }
        }
        guideRepository.delete(currentGuide);
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
