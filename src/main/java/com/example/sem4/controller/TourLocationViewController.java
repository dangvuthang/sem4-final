/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sem4.controller;

import com.example.sem4.exception.ResourceNotFoundException;
import com.example.sem4.model.Location;
import com.example.sem4.model.Tour;
import com.example.sem4.model.TourLocation;
import com.example.sem4.repository.LocationRepository;
import com.example.sem4.repository.TourLocationRepository;
import com.example.sem4.repository.TourRepository;
import com.example.sem4.util.JwtUtil;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class TourLocationViewController {

    @Autowired
    private TourLocationRepository tourLocationRepository;

    @Autowired
    private TourRepository tourRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/admin/tour_locations")
    public String getAllTourLocations(HttpServletRequest request, ModelMap model, Model attr) {
        if (request.getSession().getAttribute("username") == null) {
            return "redirect:/admin";
        } else {
            model.addAttribute("user", request.getSession().getAttribute("username"));
            model.addAttribute("list", tourLocationRepository.findAll());
            model.addAttribute("msg", (String) attr.asMap().get("msg"));
            model.addAttribute("listTours", tourRepository.findAll());
            model.addAttribute("listLocations", locationRepository.findAll());
            return "list_tour_locations";
        }
    }

    @PostMapping("/admin/tour_locations")
    public String addTourLocation(RedirectAttributes redirect, String day, String time, TourLocation tourLocation) throws ResourceNotFoundException {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat formattime = new SimpleDateFormat("HH:mm");
            Date date = format.parse(day + " " + time + ":00");
            String times = formattime.format(date);
            tourLocation.setDate(date);
            for (TourLocation tl : tourLocationRepository.findAll()) {
                if (tl.getTourId().getId() == tourLocation.getTourId().getId()
                        && tl.getLocationId().getId() == tourLocation.getLocationId().getId()) {
                    redirect.addFlashAttribute("msg", "Location '" + tourLocation.getLocationId().getName() + "' for tour '" + tourLocation.getTourId().getName() + "' existed!!!");
                    return "redirect:/admin/tour_locations";
                }
            }

            for (Tour tour : tourRepository.findAll()) {
                if ((date.getTime() < tour.getStartDate().getTime()
                        || date.getTime() > tour.getEndDate().getTime()) && tourLocation.getTourId().getId() == tour.getId()) {
                    redirect.addFlashAttribute("msg", "Date for location '" + tourLocation.getLocationId().getName() + "' "
                            + "must from '" + tour.getStartDate() + "' to '" + tour.getEndDate() + "'!!!");
                    return "redirect:/admin/tour_locations";
                }
            }
            Location updateLocation = locationRepository.findById(tourLocation.getLocationId().getId()).get();
            if (updateLocation != null) {
            } else {
                redirect.addFlashAttribute("msg", "Location '" + tourLocation.getLocationId().getId() + "' not found!!!");
                return "redirect:/admin/tour_locations";
            }
            Tour updateTour = tourRepository.findById(tourLocation.getTourId().getId()).get();
            if (updateLocation != null) {
            } else {
                redirect.addFlashAttribute("msg", "Tour with id '" + tourLocation.getLocationId().getId() + "' not found!!!");
                return "redirect:/admin/tour_locations";
            }
            if (tourLocationRepository.save(tourLocation) != null) {
                redirect.addFlashAttribute("msg", "Add success!!!");
                return "redirect:/admin/tour_locations";
            } else {
                redirect.addFlashAttribute("msg", "Add failed!!!");
                return "redirect:/admin/tour_locations";
            }
        } catch (Exception e) {
            redirect.addFlashAttribute("msg", "Failed!!!");
            return "redirect:/admin/tour_locations";
        }
    }

    @PostMapping("/admin/tour_locations/update")
    public String updateTourLocationById(RedirectAttributes redirect, String day, String time, TourLocation tourLocation) throws ResourceNotFoundException, ParseException {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat formattime = new SimpleDateFormat("HH:mm");
            Date date = format.parse(day + " " + time + ":00");
            String times = formattime.format(date);
            tourLocation.setDate(date);
            for (Tour tour : tourRepository.findAll()) {
                if ((date.getTime() < tour.getStartDate().getTime()
                        || date.getTime() > tour.getEndDate().getTime()) && tourLocation.getTourId().getId() == tour.getId()) {
                    redirect.addFlashAttribute("msg", "Date for location '" + tourLocation.getLocationId().getName() + "' "
                            + "must from '" + tour.getStartDate() + "' to '" + tour.getEndDate() + "'!!!");
                    return "redirect:/admin/tour_locations";
                }
            }
            Location updateLocation = locationRepository.findById(tourLocation.getLocationId().getId()).get();
            if (updateLocation != null) {
                tourLocation.setLocationId(updateLocation);
            } else {
                redirect.addFlashAttribute("msg", "Location '" + tourLocation.getLocationId().getId() + "' not found!!!");
                return "redirect:/admin/tour_locations";
            }
            Tour updateTour = tourRepository.findById(tourLocation.getTourId().getId()).get();
            if (updateTour != null) {
                tourLocation.setTourId(updateTour);
            } else {
                redirect.addFlashAttribute("msg", "Tour with id '" + tourLocation.getLocationId().getId() + "' not found!!!");
                return "redirect:/admin/tour_locations";
            }
            if (tourLocationRepository.save(tourLocation) != null) {
                redirect.addFlashAttribute("msg", "Update success!!!");
                return "redirect:/admin/tour_locations";
            } else {
                redirect.addFlashAttribute("msg", "Update failed!!!");
                return "redirect:/admin/tour_locations";
            }
        } catch (Exception e) {
            redirect.addFlashAttribute("msg", "Failed!!!");
            return "redirect:/admin/tour_locations";
        }
    }

    @PostMapping("admin/tour_locations/delete")
    public String deleteTourLocation(Integer id, RedirectAttributes redirect) throws ResourceNotFoundException {
        TourLocation currentTourLocation = tourLocationRepository.findById(id).get();
        try {
            if (currentTourLocation != null) {
                tourLocationRepository.delete(currentTourLocation);
                redirect.addFlashAttribute("msg", "Delete success!!!");
                return "redirect:/admin/tour_locations";
            } else {
                redirect.addFlashAttribute("msg", "Delete failed!!!");
                return "redirect:/admin/tour_locations";
            }
        } catch (Exception e) {
            redirect.addFlashAttribute("msg", "Failed!!!");
            return "redirect:/admin/tour_locations";
        }

    }
}
