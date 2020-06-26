/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sem4.controller;

import com.example.sem4.exception.ResourceNotFoundException;
import com.example.sem4.model.Booking;
import com.example.sem4.model.Location;
import com.example.sem4.model.TourLocation;
import com.example.sem4.repository.BookingRepository;
import com.example.sem4.repository.LocationRepository;
import com.example.sem4.repository.TourLocationRepository;
import com.example.sem4.util.JwtUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LocationViewController {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private TourLocationRepository tourLocationRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/admin/locations")
    public String getAllLocations(HttpServletRequest request, ModelMap model, Model attr) {
        if (request.getSession().getAttribute("username") == null) {
            return "redirect:/admin";
        } else {
            model.addAttribute("user", request.getSession().getAttribute("username"));
            model.addAttribute("msg", attr.asMap().get("msg"));
            model.addAttribute("list", locationRepository.findAll());
            return "list_locations";
        }
    }

    @PostMapping("/admin/locations")
    public String addLocation(Location location, RedirectAttributes redirect) throws ResourceNotFoundException {
        try {
            for (Location l : locationRepository.findAll()) {
                if (l.getName().toLowerCase().trim().equals(location.getName().toLowerCase().trim())) {
                    redirect.addFlashAttribute("msg", "Location with name '" + location.getName() + "' existed!!!");
                    return "redirect:/admin/locations";
                }
            }
            if (locationRepository.save(location) != null) {
                redirect.addFlashAttribute("msg", "Add success!!!");
                return "redirect:/admin/locations";
            } else {
                redirect.addFlashAttribute("msg", "Add failed!!!");
                return "redirect:/admin/locations";
            }
        } catch (Exception e) {
            redirect.addFlashAttribute("msg", "Failed!!!");
            return "redirect:/admin/locations";
        }
    }

    @PostMapping("/admin/locations/update")
    public String updateLocationById(Location location, RedirectAttributes redirect) throws ResourceNotFoundException {
        try {
            Location currentLocation = locationRepository.findById(location.getId()).get();
            currentLocation.setName(location.getName());
            currentLocation.setLongitude(location.getLongitude());
            currentLocation.setLatitude(location.getLatitude());
            currentLocation.setAddress(location.getAddress());
            if (locationRepository.save(location) != null) {
                redirect.addFlashAttribute("msg", "Update success!!!");
                return "redirect:/admin/locations";
            } else {
                redirect.addFlashAttribute("msg", "Update failed!!!");
                return "redirect:/admin/locations";
            }
        } catch (Exception e) {
            redirect.addFlashAttribute("msg", "Failed!!!");
            return "redirect:/admin/locations";
        }
    }

    @PostMapping("/admin/locations/active")
    public String deleteLocation(Integer id, RedirectAttributes redirect) throws ResourceNotFoundException {
        try {
            Location currentLocation = locationRepository.findById(id).get();
            if (currentLocation != null) {
                if (currentLocation.isIsActive()) {
                    for (TourLocation tourLocation : tourLocationRepository.findAll()) {
                        for (Booking booking : bookingRepository.findAll()) {
                            if (booking.getTourId().getId() == tourLocation.getTourId().getId()
                                    && (booking.getStartDate().getTime() >= tourLocation.getTourId().getStartDate().getTime()
                                    && booking.getEndDate().getTime() <= tourLocation.getTourId().getEndDate().getTime())) {
                                redirect.addFlashAttribute("msg", "Can't deactive because tour booked!!!");
                                return "redirect:/admin/locations";
                            }
                        }
                    }
                    currentLocation.setIsActive(false);
                    locationRepository.save(currentLocation);
                    redirect.addFlashAttribute("msg", "Deactive success!!!");
                    return "redirect:/admin/locations";
                } else {
                    currentLocation.setIsActive(true);
                    locationRepository.save(currentLocation);
                    redirect.addFlashAttribute("msg", "Active success!!!");
                    return "redirect:/admin/locations";
                }
            }
            redirect.addFlashAttribute("msg", "Location with id '" + id + "' not found!!!");
            return "redirect:/admin/locations";
        } catch (Exception e) {
            redirect.addFlashAttribute("msg", "Failed!!!");
            return "redirect:/admin/locations";
        }
    }
}
