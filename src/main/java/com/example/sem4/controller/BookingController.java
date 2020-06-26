/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sem4.controller;

import com.example.sem4.exception.ResourceNotFoundException;
import com.example.sem4.model.Booking;
import com.example.sem4.model.Guide;
import com.example.sem4.model.Tour;
import com.example.sem4.model.User;
import com.example.sem4.repository.BookingRepository;
import com.example.sem4.repository.GuideRepository;
import com.example.sem4.repository.TourRepository;
import com.example.sem4.repository.UserRepository;
import com.example.sem4.util.JwtUtil;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
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
import org.springframework.web.bind.annotation.RequestParam;
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
  private GuideRepository guideRepository;

  @Autowired
  private JwtUtil jwtUtil;

  @GetMapping(value = "bookings")
  public List<Booking> getAllBookings() {
    return bookingRepository.findAll();
  }

  @GetMapping(value = "bookings/{id}")
  public ResponseEntity<?> getAllBookingsOfUser(@PathVariable(name = "id") Integer id, @RequestParam Optional<Boolean> upcoming, @RequestParam Optional<Boolean> past) {
    List<Booking> list = bookingRepository.findByUserId(new User(id));
    if (upcoming.isPresent()) {
      list = list.stream().filter(booking -> booking.getStartDate().after(new Date())).collect(Collectors.toList());
    }
    if (past.isPresent()) {
      list = list.stream().filter(booking -> booking.getEndDate().before(new Date())).collect(Collectors.toList());
    }
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

    Date chosenTourStartDate = tour.getStartDate();
    Date chosenTourEndDate = tour.getEndDate();

//   Check if user is a guide
    if (currentUser.getRoleId().getId() == 2) {
//      Check if a guide book his/her own guide
      Guide guide = guideRepository.findByUserId(new User(currentUser.getId())).orElseThrow(() -> new ResourceNotFoundException("Can not found guide with a given id " + tourId));
      if (guide.getId() == tour.getGuideId().getId()) {
        response.put("status", "fail");
        response.put("message", "You can not book your own tour.");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
      }
//      Check if the current tour date coincide which the guide's schedule
      Stream<Tour> listOfToursByGuide = tourRepository.findByGuideId(new Guide(guide.getId())).stream();
      boolean result1 = listOfToursByGuide.anyMatch(tourSchedule -> {
        Date scheduleStartDate = tourSchedule.getStartDate();
        Date scheduleEndDate = tourSchedule.getStartDate();
        boolean isCoincide = chosenTourStartDate.compareTo(scheduleStartDate) * scheduleStartDate.compareTo(chosenTourEndDate) >= 0 || chosenTourStartDate.compareTo(scheduleEndDate) * scheduleEndDate.compareTo(chosenTourEndDate) >= 0;
        return isCoincide;
      });
      if (result1) {
        response.put("status", "fail");
        response.put("message", "This tour date is coincide with your schedule");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
      }
    }
//    Check if user has already booked this tour
    Stream<Booking> list1 = bookingRepository.findByUserId(new User(currentUser.getId())).stream();
    boolean result1 = list1.anyMatch(booking -> booking.getTourId().getId() == tourId && booking.getStartDate().equals(tour.getStartDate()));
    if (result1) {
      response.put("status", "fail");
      response.put("message", "You have already booked this tour.");
      return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
//  Check if within the new tour booked, there is any date that will coincide
    Stream<Booking> list2 = bookingRepository.findByUserId(new User(currentUser.getId())).stream();
    boolean result2 = list2.anyMatch(booking -> {
      Date bookingTourStartDate = booking.getStartDate();
      Date bookingTourEndDate = booking.getEndDate();
      boolean isCoincide = chosenTourStartDate.compareTo(bookingTourStartDate) * bookingTourStartDate.compareTo(chosenTourEndDate) >= 0 || chosenTourStartDate.compareTo(bookingTourEndDate) * bookingTourEndDate.compareTo(chosenTourEndDate) >= 0;
      return isCoincide;
    });
    if (result2) {
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
