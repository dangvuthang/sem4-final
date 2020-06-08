/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sem4.controller;

import com.example.sem4.exception.ResourceNotFoundException;
import com.example.sem4.model.AuthenticationProvider;
import com.example.sem4.model.AuthenticationRequest;
import com.example.sem4.model.Role;
import com.example.sem4.model.User;
import com.example.sem4.repository.AuthenticationProviderRepository;
import com.example.sem4.repository.UserRepository;
import com.example.sem4.util.JwtUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private AuthenticationProviderRepository authenticationProviderRepository;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private JwtUtil jwtUtil;

  @GetMapping("users")
  public List<User> getAllUsers() {
    return userRepository.findByRoleId(1);
  }

  @GetMapping("users/{id}")
  public ResponseEntity<User> getUserById(@PathVariable(name = "id") Integer userId) throws ResourceNotFoundException {
    User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Can not found user with a given id: " + userId));
    return ResponseEntity.ok(user);
  }

  @PutMapping("admin/users/{id}")
  public ResponseEntity<User> updateUserById(@PathVariable(name = "id") Integer userId, @RequestBody User user) throws ResourceNotFoundException {
    User currentUser = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Can not found user with a given id: " + userId));
    currentUser.setName(user.getName());
    currentUser.setEmail(user.getEmail());
    currentUser.setPassword(user.getPassword());
    currentUser.setPhone(user.getPhone());
    currentUser.setActive(user.getActive());
    return ResponseEntity.ok(userRepository.save(currentUser));
  }

  @PutMapping("admin/users/active/{id}")
  public ResponseEntity<User> activeUser(@PathVariable(name = "id") Integer userId) throws ResourceNotFoundException {
    User currentUser = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Can not found user with a given id: " + userId));
    if (currentUser.getActive()) {
      currentUser.setActive(false);
    } else {
      currentUser.setActive(true);
    }
    return ResponseEntity.ok(userRepository.save(currentUser));
  }
//  @PostMapping(value = "/users/login",consumes = "text/plain")

  @PostMapping(value = "/users/login")
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

  @PostMapping(value = "/users/signinWithGoogle")
  public ResponseEntity<?> signinWithGoogle(@RequestBody Map<String, String> json) throws Exception {
    Optional<AuthenticationProvider> a = authenticationProviderRepository.findByProviderKey(json.get("providerKey"));
    if (a.isPresent()) {
      String email = a
              .get().getUserId().getEmail();
      String jwt = jwtUtil.generateToken(email);
      Map<String, String> response = new HashMap<>();
      response.put("jwt", jwt);
      response.put("email", email);
      return ResponseEntity.ok().body(response);
    }
    User u = new User();
    u.setEmail(json.get("email"));
    u.setPhone(json.get("phone"));
    u.setActive(true);
    u.setName(json.get("name"));
    u.setAvatarImage(json.get("avatarImage"));
    u.setRoleId(new Role(1));
    User user = userRepository.save(u);
    AuthenticationProvider authenticationProvider = new AuthenticationProvider();
    authenticationProvider.setProviderKey(json.get("providerKey"));
    authenticationProvider.setUserId(user);
    authenticationProviderRepository.save(authenticationProvider);
    String jwt = jwtUtil.generateToken(user.getEmail());
    Map<String, String> response = new HashMap<>();
    response.put("jwt", jwt);
    response.put("email", user.getEmail());
    return ResponseEntity.ok().body(response);
  }

  @PostMapping("/users/signup")
  public ResponseEntity<?> signup(@Valid @RequestBody User user) throws Exception {
    user.setRoleId(new Role(1));
    userRepository.save(user);
    String jwt = jwtUtil.generateToken(user.getEmail());
    Map<String, String> response = new HashMap<>();
    response.put("jwt", jwt);
    response.put("email", user.getEmail());
    return ResponseEntity.ok().body(response);
  }

}
