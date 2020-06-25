/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sem4.controller;

import com.example.sem4.exception.ResourceNotFoundException;
import com.example.sem4.model.Guide;
import com.example.sem4.model.GuideFormWrapper;
import com.example.sem4.model.User;
import com.example.sem4.repository.GuideRepository;
import com.example.sem4.repository.UserRepository;
import com.example.sem4.service.CloudinaryService;
import com.example.sem4.util.JwtUtil;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class GuideController {
  
  @Autowired
  private GuideRepository guideRepository;
  
  @Autowired
  private UserRepository userRepository;
  
  @Autowired
  private AuthenticationManager authenticationManager;
  
  @Autowired
  private JwtUtil jwtUtil;
  
  @Autowired
  private CloudinaryService cloudinaryService;
  
  @GetMapping("/guides")
  public List<Guide> getAllGuides() {
    return guideRepository.findAll();
  }
  
  @GetMapping("/guides/{userId}")
  public ResponseEntity<Guide> getGuideByUserId(@PathVariable(name = "userId") Integer userId) throws ResourceNotFoundException {
    Guide guide = guideRepository.findByUserId(new User(userId)).orElseThrow(() -> new ResourceNotFoundException("Can not found Guide with a given id: " + userId));
    return ResponseEntity.ok(guide);
  }

  //  Update current guide's info
  @RequestMapping(path = "/guides/{userId}", method = RequestMethod.PUT,
          consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<?> updateGuideByUserId(@PathVariable(name = "userId") Integer userId,
          @ModelAttribute GuideFormWrapper model) throws ResourceNotFoundException, IOException {
    Guide currentGuide = guideRepository.findByUserId(new User(userId)).orElseThrow(() -> new ResourceNotFoundException("Can not found Guide with a given id: " + userId));
    currentGuide.getUserId().setName(model.getName());
    currentGuide.getUserId().setPhone(model.getPhone());
    currentGuide.setDescription(model.getDescription());
    if (model.getAvatarImage() != null) {
      Map result = cloudinaryService.upload(model.getAvatarImage());
      String imageName = (String) result.get("url");
      currentGuide.getUserId().setAvatarImage(imageName);
    }
    Guide updatedGuide = guideRepository.save(currentGuide);
    Map<String, String> response = new HashMap<>();
    String jwt = jwtUtil.generateToken(updatedGuide.getUserId().getEmail());
    response.put("jwt", jwt);
    response.put("email", updatedGuide.getUserId().getEmail());
    response.put("name", updatedGuide.getUserId().getName());
    response.put("avatarImage", updatedGuide.getUserId().getAvatarImage());
    return ResponseEntity.ok().body(response);
  }
  
}
