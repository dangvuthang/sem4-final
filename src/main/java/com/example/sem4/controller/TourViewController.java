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
import com.example.sem4.model.TourType;
import com.example.sem4.repository.BookingRepository;
import com.example.sem4.repository.GuideRepository;
import com.example.sem4.repository.TourLocationRepository;
import com.example.sem4.repository.TourRepository;
import com.example.sem4.repository.TourTypeRepository;
import com.example.sem4.repository.UserRepository;
import com.example.sem4.util.JwtUtil;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class TourViewController {

    @Autowired
    private TourRepository tourRepository;

    @Autowired
    private TourLocationRepository tourLocationRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private GuideRepository guideRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TourTypeRepository tourTypeRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/admin/tours")
    public String getAllTours(HttpServletRequest request, ModelMap model, Model attr) throws URISyntaxException {
        if (request.getSession().getAttribute("username") == null) {
            return "redirect:/admin";
        } else {
            model.addAttribute("user", request.getSession().getAttribute("username"));
            model.addAttribute("list", tourRepository.findAll());
            model.addAttribute("listGuides", guideRepository.findAll());
            model.addAttribute("listTypes", tourTypeRepository.findAll());
            String msg = (String) attr.asMap().get("msg");
            model.addAttribute("msg", msg);
            return "list_tours";
        }
    }

    @GetMapping("/admin/tours/{id}")
    public ResponseEntity<Tour> getTourById(@PathVariable("id") Integer tourId) throws ResourceNotFoundException {
        Tour tour = tourRepository.findById(tourId).orElseThrow(() -> new ResourceNotFoundException("Can not found Tour with a given id: " + tourId));
        return ResponseEntity.ok(tour);
    }

    @PostMapping(value = "/admin/tours", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String addTour(HttpServletRequest request, Tour tour, ModelMap model, MultipartFile file, String start_Date, String end_Date, RedirectAttributes redirect) throws ResourceNotFoundException {
        try {
            if (file.getOriginalFilename() != null) {
                try {
                    //    Get Path to save image
                    String rootPath = new FileSystemResource("").getFile().getAbsolutePath();
                    Path path = Paths.get(rootPath + "/src/main/resources/static/images/" + file.getOriginalFilename());
                    byte[] bytes = file.getBytes();
                    Files.write(path, bytes);
                } catch (IOException ex) {
                    redirect.addFlashAttribute("msg", "Failed!!!");
                    return "redirect:/admin/tour_images";
                }
            }
            for (Tour t : tourRepository.findAll()) {
                if (t.getName().toLowerCase().equals(tour.getName().toLowerCase())) {
                    redirect.addFlashAttribute("msg", "Tour with name '" + tour.getName() + "' existed!!!");
                    return "redirect:/admin/tours";
                }
            }
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            tour.setStartDate(format.parse(start_Date));
            tour.setEndDate(format.parse(end_Date));
            Guide currentGuide = guideRepository.findById(tour.getGuideId().getId()).get();
            if (currentGuide != null) {

            } else {
                redirect.addFlashAttribute("msg", "Guide with id '" + tour.getGuideId().getId() + "' not found!!!");
                return "redirect:/admin/tours";
            }
            TourType currentTourType = tourTypeRepository.findById(tour.getTourTypeId().getId()).get();
            if (currentTourType != null) {

            } else {
                redirect.addFlashAttribute("msg", "Type with id '" + tour.getTourTypeId().getId() + "' not found!!!");
                return "redirect:/admin/tours";
            }
            if (tourRepository.save(tour) != null) {
                redirect.addFlashAttribute("msg", "Add success!!!");
                return "redirect:/admin/tours";
            } else {
                redirect.addFlashAttribute("msg", "Add failed!!!");
                return "redirect:/admin/tours";
            }
        } catch (Exception ex) {
            redirect.addAttribute("msg", "Failed!!!");
            return "redirect:/admin/tours";
        }
    }

    @PostMapping(value = "/admin/tours/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String updateTourById(HttpServletRequest request, RedirectAttributes redirect, Tour tour, MultipartFile file, String start_Date, String end_Date) throws ResourceNotFoundException {
        try {
            Tour currentTour = tourRepository.findById(tour.getId()).get();
            if (currentTour != null) {
                try {
                    if (file.getOriginalFilename() != null) {
                        try {
                            //    Get Path to save image
                            String rootPath = new FileSystemResource("").getFile().getAbsolutePath();
                            Path path = Paths.get(rootPath + "/src/main/resources/static/images/" + file.getOriginalFilename());
                            byte[] bytes = file.getBytes();
                            Files.write(path, bytes);
                        } catch (IOException ex) {
                            redirect.addFlashAttribute("msg", "Failed!!!");
                            return "redirect:/admin/tours";
                        }
                    }
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    Date startDate = format.parse(start_Date);
                    Date endDate = format.parse(end_Date);
                    currentTour.setDescription(tour.getDescription());
                    currentTour.setDuration(tour.getDuration());
                    currentTour.setMaxGroupSize(tour.getMaxGroupSize());
                    currentTour.setSummary(tour.getSummary());
                    for (Booking booking : bookingRepository.findAll()) {
                        if (booking.getTourId().getId() == currentTour.getId()
                                && booking.getTourId().getName().toLowerCase().equals(tour.getName().toLowerCase())) {

                            currentTour.setName(tour.getName());
                        } else if (booking.getTourId().getId() == currentTour.getId()
                                && !booking.getTourId().getName().toLowerCase().equals(tour.getName().toLowerCase())) {
                            redirect.addFlashAttribute("msg", "Can't update!!!");
                            return "redirect:/admin/tours";
                        } else {

                        }
                        if (booking.getTourId().getId() == currentTour.getId()
                                && (booking.getCreatedAt().getTime() > startDate.getTime()
                                || booking.getCreatedAt().getTime() >= endDate.getTime())) {
                            redirect.addFlashAttribute("msg", "New Start date or End date must be greater than old booking created date!!!");
                            return "redirect:/admin/tours";
                        } else {

                        }
                    }
                    currentTour.setName(tour.getName());
                    currentTour.setStartDate(startDate);
                    currentTour.setEndDate(endDate);
                    currentTour.setPrice(tour.getPrice());
                    Guide currentGuide = guideRepository.findById(tour.getGuideId().getId()).get();
                    if (currentGuide != null) {
                        currentTour.setGuideId(currentGuide);
                    } else {
                        redirect.addFlashAttribute("msg", "Guide with id '" + tour.getGuideId().getId() + "' not found!!!");
                        return "redirect:/admin/tours";
                    }
                    TourType currentTourType = tourTypeRepository.findById(tour.getTourTypeId().getId()).get();
                    if (currentTourType != null) {
                        currentTour.setTourTypeId(currentTourType);
                    } else {
                        redirect.addFlashAttribute("msg", "Type with id '" + tour.getTourTypeId().getId() + "' not found!!!");
                        return "redirect:/admin/tours";
                    }
                    currentTour.setPriceDiscount(tour.getPriceDiscount());
                    currentTour.setTourImageCover(tour.getTourImageCover());
                    if (tourRepository.save(currentTour) != null) {
                        redirect.addFlashAttribute("msg", "Update success!!!");
                        return "redirect:/admin/tours";
                    } else {
                        redirect.addAttribute("msg", "Update failed!!!");
                        return "redirect:/admin/tours";
                    }
                } catch (Exception ex) {
                    redirect.addAttribute("msg", "Failed!!!");
                    return "redirect:/admin/tours";
                }
            }
            redirect.addAttribute("msg", "Update failed!!!");
            return "redirect:/admin/tours";
        } catch (Exception e) {
            redirect.addAttribute("msg", "Failed!!!");
            return "redirect:/admin/tours";
        }
    }

    @PostMapping("/admin/tours/active")
    public String activeTour(Integer tourId, RedirectAttributes redirect) throws ResourceNotFoundException {
        try {
            Tour currentTour = tourRepository.findById(tourId).get();
            if (currentTour != null) {

                if (currentTour.getActive()) {
                    for (Booking booking : bookingRepository.findAll()) {
                        if (booking.getTourId().getId() == currentTour.getId() && booking.getCreatedAt().getTime() < currentTour.getEndDate().getTime()
                                && booking.getCreatedAt().getTime() >= currentTour.getStartDate().getTime()) {
                            redirect.addFlashAttribute("msg", "Can't deactive!!!");
                            return "redirect:/admin/tours";
                        }
                    }
                    currentTour.setActive(false);
                    if (tourRepository.save(currentTour) != null) {
                        redirect.addFlashAttribute("msg", "Deactive success!!!");
                        return "redirect:/admin/tours";
                    } else {
                        redirect.addFlashAttribute("msg", "Deactive failed!!!");
                        return "redirect:/admin/tours";
                    }
                } else {
                    currentTour.setActive(true);
                    if (tourRepository.save(currentTour) != null) {
                        redirect.addFlashAttribute("msg", "Active success!!!");
                        return "redirect:/admin/tours";
                    } else {
                        redirect.addFlashAttribute("msg", "Active failed!!!");
                        return "redirect:/admin/tours";
                    }
                }

            } else {
                redirect.addFlashAttribute("msg", "Tour with id '" + tourId + "' not found!!!");
                return "redirect:/admin/tours";
            }
        } catch (Exception e) {
            redirect.addFlashAttribute("msg", "Failed!!!");
            return "redirect:/admin/tours";
        }
    }
}
