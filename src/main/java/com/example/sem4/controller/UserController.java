/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sem4.controller;

import com.example.sem4.exception.ResourceNotFoundException;
import com.example.sem4.model.AuthenticationRequest;
import com.example.sem4.model.User;
import com.example.sem4.repository.UserRepository;
import com.example.sem4.util.JwtUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
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
public class UserController {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private JwtUtil jwtUtil;

  @GetMapping("users")
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  @GetMapping("/users/{id}")
  public ResponseEntity<User> getUserById(@PathVariable(name = "id") Long userId) throws ResourceNotFoundException {
    User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Can not found user with a given id: " + userId));
    return ResponseEntity.ok(user);
  }

  @PutMapping("/users/{id}")
  public ResponseEntity<User> updateUserById(@PathVariable(name = "id") Long userId, @RequestBody User user) throws ResourceNotFoundException {
    User currentUser = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Can not found user with a given id: " + userId));
    currentUser.setEmail(user.getEmail());
    currentUser.setName(user.getName());
    return ResponseEntity.ok(userRepository.save(currentUser));
  }

  @DeleteMapping("/users/{id}")
  public Map<String, Boolean> deleteUser(@PathVariable(name = "id") Long userId) throws ResourceNotFoundException {
    User currentUser = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Can not found user with a given id: " + userId));
    userRepository.delete(currentUser);
    Map<String, Boolean> response = new HashMap<>();
    response.put("deleted", Boolean.TRUE);
    return response;
  }

  @PostMapping("/users/login")
  public ResponseEntity<?> login(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
    try {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));
    } catch (AuthenticationException e) {
      throw new Exception("Incorrect username or password");
    }

    String jwt = jwtUtil.generateToken(authenticationRequest.getEmail());
    Map<String, String> response = new HashMap<>();
    response.put("jwt", jwt);
    response.put("email", authenticationRequest.getEmail());

    return ResponseEntity.ok().body(response);
  }

  @PostMapping("/users/signup")
  public ResponseEntity<?> signup(@Valid @RequestBody User user) throws Exception {
    userRepository.save(user);
    String jwt = jwtUtil.generateToken(user.getEmail());
    Map<String, String> response = new HashMap<>();
    response.put("jwt", jwt);
    response.put("email", user.getEmail());

    return ResponseEntity.ok().body(response);
  }

}
