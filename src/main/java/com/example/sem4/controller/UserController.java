/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sem4.controller;

import com.example.sem4.exception.ResourceNotFoundException;
import com.example.sem4.model.AuthenticationProvider;
import com.example.sem4.model.AuthenticationRequest;
import com.example.sem4.model.FormWrapper;
import com.example.sem4.model.Role;
import com.example.sem4.model.User;
import com.example.sem4.repository.AuthenticationProviderRepository;
import com.example.sem4.repository.UserRepository;
import com.example.sem4.service.CloudinaryService;
import com.example.sem4.util.JwtUtil;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
  ServletContext context;

  @Autowired
  private JwtUtil jwtUtil;

  @Autowired
  private CloudinaryService cloudinaryService;

//  Get current user
  @GetMapping("/users/{email}")
  public ResponseEntity<?> getUserByEmail(@PathVariable(name = "email") String email) throws ResourceNotFoundException {
    User user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Can not found user with a given email: " + email));
    return ResponseEntity.ok(user);
  }

//  Update current user's info
  @RequestMapping(path = "/users/{email}", method = RequestMethod.PUT,
          consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<?> updateUserByEmail(@PathVariable(name = "email") String email,
          @ModelAttribute FormWrapper model) throws ResourceNotFoundException, IOException {
    User currentUser = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Can not found user with a given email: " + email));
    currentUser.setName(model.getName());
    currentUser.setPhone(model.getPhone());
    if (model.getAvatarImage() != null) {
      Map result = cloudinaryService.upload(model.getAvatarImage());
      String imageName = (String) result.get("url");
      currentUser.setAvatarImage(imageName);
    }
    User updatedUser = userRepository.save(currentUser);
    Map<String, String> response = new HashMap<>();
    String jwt = jwtUtil.generateToken(updatedUser.getEmail());
    response.put("jwt", jwt);
    response.put("email", updatedUser.getEmail());
    response.put("name", updatedUser.getName());
    response.put("avatarImage", updatedUser.getAvatarImage());
    return ResponseEntity.ok().body(response);
  }

//  Update current user's password
  @RequestMapping(path = "/users/password/{email}", method = RequestMethod.PUT)
  public ResponseEntity<?> updateUserPassword(@PathVariable(name = "email") String email,
          @RequestBody Map<String, String> json) throws ResourceNotFoundException {
    User currentUser = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Can not found user with a given email: " + email));
    Map<String, String> response = new HashMap<>();
    String oldPassword = json.get("oldPassword");
    String newPassword = json.get("newPassword");
    String passwordConfirm = json.get("passwordConfirm");
    if (!currentUser.getPassword().equals(oldPassword)) {
      response.put("status", "fail");
      response.put("message", "Incorrect old password");
      return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    if (!newPassword.equals(passwordConfirm)) {
      response.put("status", "fail");
      response.put("message", "New password and password confirm not match. Try again !!!");
      return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    currentUser.setPassword(newPassword);
    User updatedUser = userRepository.save(currentUser);
    String jwt = jwtUtil.generateToken(updatedUser.getEmail());
    response.put("jwt", jwt);
    response.put("email", updatedUser.getEmail());
    response.put("name", updatedUser.getName());
    response.put("avatarImage", updatedUser.getAvatarImage());
    return ResponseEntity.ok().body(response);
  }

//  Deactive current user's account
  @RequestMapping(path = "/users/{email}", method = RequestMethod.DELETE)
  public ResponseEntity<?> deactiveUser(@PathVariable(name = "email") String email) throws ResourceNotFoundException {
    User user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Can not found user with a given email: " + email));
    user.setActive(false);
    userRepository.save(user);
    Map<String, String> response = new HashMap<>();
    response.put("status", "success");
    response.put("message", "Successfully Deactive your account");
    return ResponseEntity.ok().body(response);
  }

  @PostMapping(value = "/users/login")
  public ResponseEntity<?> login(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
    Map<String, String> response = new HashMap<>();
    User u = userRepository.findByEmail(authenticationRequest.getEmail()).orElseThrow(() -> new ResourceNotFoundException("No user found with a given email: " + authenticationRequest.getEmail()));
    if (!u.getPassword().equals(authenticationRequest.getPassword())) {
      response.put("message", "Incorrect password. Try again!");
      return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    if (u.getActive() == false) {
      response.put("status", "fail");
      response.put("message", "Your Account is already deactivated. Please contact admin.");
      return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    String jwt = jwtUtil.generateToken(authenticationRequest.getEmail());
    response.put("jwt", jwt);
    response.put("email", authenticationRequest.getEmail());
    response.put("name", u.getName());
    response.put("avatarImage", u.getAvatarImage());
    return ResponseEntity.ok().body(response);
  }

  @PostMapping(value = "/users/signinWithGoogle")
  public ResponseEntity<?> signinWithGoogle(@RequestBody Map<String, String> json) throws Exception {
    Optional<AuthenticationProvider> a = authenticationProviderRepository.findByProviderKey(json.get("providerKey"));
    if (a.isPresent()) {
      String email = a.get().getUserId().getEmail();
      String name = a.get().getUserId().getName();
      String avatarImage = a.get().getUserId().getAvatarImage();
      String jwt = jwtUtil.generateToken(email);
      Map<String, String> response = new HashMap<>();
      response.put("jwt", jwt);
      response.put("email", email);
      response.put("name", name);
      response.put("avatarImage", avatarImage);
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
    response.put("name", user.getName());
    response.put("avatarImage", user.getAvatarImage());
    return ResponseEntity.ok().body(response);
  }

  @RequestMapping(path = "/users/signup", method = RequestMethod.POST,
          consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<?> signup(@ModelAttribute FormWrapper model) throws Exception {
    Map<String, String> response = new HashMap<>();
    if (userRepository.findByEmail(model.getEmail()).isPresent()) {
      response.put("status", "fail");
      response.put("message", "Email is already is use!");
      return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    String imageName = "https://res.cloudinary.com/dybygufkr/image/upload/v1593000869/avatar_q2ysxd.jpg";
    if (model.getAvatarImage() != null) {
//      Save image to cloudinary
      Map result = cloudinaryService.upload(model.getAvatarImage());
      imageName = (String) result.get("url");
    }
    User u = new User();
    u.setEmail(model.getEmail());
    u.setName(model.getName());
    u.setPhone(model.getPhone());
    u.setPassword(model.getPassword());
    u.setAvatarImage(imageName);
    u.setRoleId(new Role(3));
    u.setActive(Boolean.TRUE);
    userRepository.save(u);
    String jwt = jwtUtil.generateToken(u.getEmail());
    response.put("jwt", jwt);
    response.put("email", u.getEmail());
    response.put("name", u.getName());
    response.put("avatarImage", u.getAvatarImage());
    return ResponseEntity.ok().body(response);
  }

}
