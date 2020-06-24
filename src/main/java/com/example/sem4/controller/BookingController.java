/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sem4.controller;

import com.example.sem4.dto.BookingDTO;
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
import java.util.stream.Stream;
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
import org.springframework.web.bind.annotation.RequestMethod;
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
  public List<Booking> getAllBookings() {
    return bookingRepository.findAll();
  }

  @GetMapping(value = "bookings/{id}")
  public ResponseEntity<?> getAllBookingsOfUser(@PathVariable(name = "id") Integer id) {
    List<Booking> list = bookingRepository.findByUserId(new User(id));
    return ResponseEntity.ok().body(list);
  }

  //  delete booking tour
  @RequestMapping(path = "/bookings/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<?> deleteBooking(@PathVariable(name = "id") Integer id) throws ResourceNotFoundException {
    Booking booking = bookingRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Can not found booking with a given id: " + id));
    Tour tour = tourRepository.findById(booking.getTourId().getId()).orElseThrow(() -> new ResourceNotFoundException("Can not found tour with a given id: " + booking.getTourId().getId()));
    tour.setCurrentGroupSize(tour.getCurrentGroupSize() - booking.getQuantity());
    bookingRepository.delete(booking);
    tourRepository.save(tour);
    Map<String, String> response = new HashMap<>();
    response.put("status", "success");
    response.put("message", "Successfully cancel your booking");
    return ResponseEntity.ok().body(response);
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
//    Check if tour has slots
    if (tour.getCurrentGroupSize() == tour.getMaxGroupSize()) {
      response.put("status", "fail");
      response.put("message", "Tour is fully booked !");
      return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
//    Check if what user book is more than the maxGroupSize
    if (tour.getCurrentGroupSize() + quantity > tour.getMaxGroupSize()) {
      response.put("status", "fail");
      response.put("message", "Only " + (tour.getMaxGroupSize() - tour.getCurrentGroupSize()) + " spots left");
      return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
//    Check if user has already booked this tour
    Stream<Booking> list1 = bookingRepository.findByUserId(new User(currentUser.getId())).stream();
    Stream<Booking> result1 = list1.filter(booking -> booking.getTourId().getId() == tourId && booking.getStartDate().equals(tour.getStartDate()));
    if (result1.count() == 1) {
      response.put("status", "fail");
      response.put("message", "You have already booked this tour.");
      return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
//  Check if within the new tour booked, there is any date that will coincide
    Stream<Booking> list2 = bookingRepository.findByUserId(new User(currentUser.getId())).stream();
    Stream<Booking> result2 = list2.filter(booking -> (tour.getStartDate().after(booking.getStartDate()) && tour.getEndDate().before(booking.getEndDate()) || (tour.getEndDate().after(booking.getStartDate()) && tour.getEndDate().before(booking.getEndDate()))));
    if (result2.count() > 0) {
      response.put("status", "fail");
      response.put("message", "The tour is coincide with one of your upcoming tour");
      return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
//  Save new Booking
    Booking booking = new Booking();
    booking.setCreatedAt(now);
    booking.setUserId(new User(currentUser.getId()));
    booking.setPrice(price);
    booking.setPaid(true);
    booking.setTourId(new Tour(tourId));
    booking.setQuantity(quantity);
    booking.setIsCancelled(true);
    booking.setStartDate(tour.getStartDate());
    booking.setEndDate(tour.getEndDate());
    tour.setCurrentGroupSize(tour.getCurrentGroupSize() + quantity);
    bookingRepository.save(booking);
    tourRepository.save(tour);
    response.put("status", "success");
    response.put("message", "Successfully Booked");
    return ResponseEntity.ok().body(response);
  }

}
