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
import com.example.sem4.util.JwtUtil;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

  @GetMapping("users/{email}")
  public ResponseEntity<User> getUserByEmail(@PathVariable(name = "email") String email) throws ResourceNotFoundException {
    User user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Can not found user with a given email: " + email));
    return ResponseEntity.ok(user);
  }

  @PutMapping("users/{email}")
  public ResponseEntity<?> updateUserByEmail(@PathVariable(name = "email") String email,
          @RequestBody User user) throws ResourceNotFoundException {
    User currentUser = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Can not found user with a given email: " + email));
    currentUser.setName(user.getName());
    currentUser.setPhone(user.getPhone());
    currentUser.setAvatarImage(user.getPhone());
    return ResponseEntity.ok(userRepository.save(currentUser));
  }

  @DeleteMapping("users/email")
  public ResponseEntity<?> deactiveUser(@PathVariable(name = "email") String email) throws ResourceNotFoundException {
    User user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Can not found user with a given email: " + email));
    user.setActive(false);
    Map<String, String> response = new HashMap<>();
    response.put("status", "Successfully Deactive your account");
    return ResponseEntity.ok().body(response);
  }

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

  @RequestMapping(path = "/users/signup", method = RequestMethod.POST,
          consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<?> signup(@ModelAttribute FormWrapper model) throws Exception {
    Map<String, String> response = new HashMap<>();
    if (userRepository.findByEmail(model.getEmail()).isPresent()) {
      response.put("status", "fail");
      response.put("message", "Email is already is use!");
      return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    String imageName = "avatar.png";
    if (model.getAvatarImage() != null) {
      //    Get Path to save image
      String rootPath = new FileSystemResource("").getFile().getAbsolutePath();
      imageName = UUID.randomUUID() + model.getAvatarImage().getOriginalFilename();
      Path path = Paths.get(rootPath + "/src/main/resources/static/images/" + imageName);
      byte[] bytes = model.getAvatarImage().getBytes();
      Files.write(path, bytes);
    }
    User u = new User();
    u.setEmail(model.getEmail());
    u.setName(model.getName());
    u.setPhone(model.getPhone());
    u.setPassword(model.getPassword());
    u.setAvatarImage(imageName);
    u.setRoleId(new Role(2));
    u.setActive(Boolean.TRUE);
    userRepository.save(u);
    String jwt = jwtUtil.generateToken(u.getEmail());
    response.put("jwt", jwt);
    response.put("email", u.getEmail());
    return ResponseEntity.ok().body(response);
  }

}
