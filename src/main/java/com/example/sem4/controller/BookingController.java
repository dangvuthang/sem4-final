/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sem4.controller;

import com.example.sem4.exception.ResourceNotFoundException;
import com.example.sem4.model.Booking;
import com.example.sem4.model.Tour;
import com.example.sem4.model.User;
import com.example.sem4.repository.BookingRepository;
import com.example.sem4.repository.TourRepository;
import com.example.sem4.repository.UserRepository;
import com.example.sem4.util.JwtUtil;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
public class BookingController {

  @Autowired
  private BookingRepository bookingRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private TourRepository tourRepository;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private JwtUtil jwtUtil;

  @GetMapping(value = "bookings")
  public ResponseEntity<?> getAllBookings() {
    return ResponseEntity.ok().body(bookingRepository.findAll());
  }

  @GetMapping(value = "bookings/{id}")
  public ResponseEntity<?> getAllBookingsOfUser(@PathVariable(name = "id") Integer id) {
    List<Booking> list = bookingRepository.findByUserId(new User(id));
    list.forEach(booking -> booking.setUserId(null));
    list.forEach(booking -> booking.getTourId().setReviewTourCollection(null));
    list.forEach(booking -> booking.getTourId().setTourLocationCollection(null));
    list.forEach(booking -> booking.getTourId().setGuideId(null));
    return ResponseEntity.ok().body(list);
  }

  @PostMapping(value = "/bookings/{email}")
  public ResponseEntity<?> updateUserPassword(@PathVariable(name = "email") String email,
          @RequestBody Map<String, String> json) throws ResourceNotFoundException {
    User currentUser = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Can not found user with a given email: " + email));
    Integer tourId = Integer.parseInt(json.get("tourId"));
    Integer quantity = Integer.parseInt(json.get("quantity"));
    Timestamp now = new Timestamp(System.currentTimeMillis());
    BigDecimal price = new BigDecimal(json.get("price"));
    Tour tour = tourRepository.findById(tourId).orElseThrow(() -> new ResourceNotFoundException("Can not found tour with a given id " + tourId));
    Map<String, String> response = new HashMap<>();
    if (tour.getCurrentGroupSize() == tour.getMaxGroupSize()) {
      response.put("status", "fail");
      response.put("message", "Tour is fully booked !");
      return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    if (tour.getCurrentGroupSize() + quantity > tour.getMaxGroupSize()) {
      response.put("status", "fail");
      response.put("message", "Only " + (tour.getMaxGroupSize() - tour.getCurrentGroupSize()) + " spots left");
      return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    Booking booking = new Booking();
    booking.setCreatedAt(now);
    booking.setUserId(new User(currentUser.getId()));
    booking.setPrice(price);
    booking.setPaid(true);
    booking.setTourId(new Tour(tourId));
    booking.setQuantity(quantity);
    booking.setIsCancelled(true);
    tour.setCurrentGroupSize(tour.getCurrentGroupSize() + quantity);
    bookingRepository.save(booking);
    tourRepository.save(tour);
    response.put("status", "success");
    response.put("message", "Successfully Booked");
    return ResponseEntity.ok().body(response);
  }

}
