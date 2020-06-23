/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sem4.controller;

import com.example.sem4.exception.ResourceNotFoundException;
import com.example.sem4.model.Tour;
import com.example.sem4.model.TourImage;
import com.example.sem4.repository.TourImageRepository;
import com.example.sem4.repository.TourRepository;
import com.example.sem4.util.JwtUtil;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class TourImageViewController {

    @Autowired
    private TourImageRepository tourImageRepository;

    @Autowired
    private TourRepository tourRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/admin/tour_images")
    public String getAllTourImages(HttpServletRequest request, ModelMap model, Model attr) {
        if (request.getSession().getAttribute("username") == null) {
            return "redirect:/admin";
        } else {
            model.addAttribute("user", request.getSession().getAttribute("username"));
            model.addAttribute("list", tourImageRepository.findAll());
            model.addAttribute("msg", attr.asMap().get("msg"));
            model.addAttribute("listTour", tourRepository.findAll());
            return "list_tour_images";
        }
    }

    @PostMapping(value = "/admin/tour_images", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String addTourImage(TourImage tourImage, RedirectAttributes redirect, MultipartFile file) throws ResourceNotFoundException {
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
            if (tourImageRepository.save(tourImage) != null) {
                redirect.addAttribute("msg", "Add success!!!");
                return "redirect:/admin/tour_images";
            } else {
                redirect.addAttribute("msg", "Add failed!!!");
                return "redirect:/admin/tour_images";
            }
        } catch (Exception e) {
            redirect.addAttribute("msg", "Failed!!!");
            return "redirect:/admin/tour_images";
        }
    }

    @PostMapping(value = "/admin/tour_images/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String updateTourImageById(TourImage tourImage, RedirectAttributes redirect, MultipartFile file) throws ResourceNotFoundException {
        try {
            if (file.getOriginalFilename() != null) {
                try {
                    //    Get Path to save image
                    String rootPath = new FileSystemResource("").getFile().getAbsolutePath();
                    Path path = Paths.get(rootPath + "/src/main/resources/static/images/" + file.getOriginalFilename());
                    byte[] bytes = file.getBytes();
                    Files.write(path, bytes);
                } catch (IOException ex) {
//                    redirect.addFlashAttribute("msg", "Failed!!!");
//                    return "redirect:/admin/tour_images";
                }
            }
            TourImage currentTourImage = tourImageRepository.findById(tourImage.getId()).get();
            currentTourImage.setImageUrl(tourImage.getImageUrl());
            Tour updateTour = tourRepository.findById(tourImage.getTourId().getId()).get();
            if (updateTour != null) {
                currentTourImage.setTourId(updateTour);
            } else {
                redirect.addFlashAttribute("msg", "Tour with id '" + tourImage.getTourId().getId() + "' not found!!!");
                return "redirect:/admin/tour_images";
            }
            if (tourImageRepository.save(currentTourImage) != null) {
                redirect.addFlashAttribute("msg", "Update success!!!");
                return "redirect:/admin/tour_images";
            } else {
                redirect.addFlashAttribute("msg", "Update failed!!!");
                return "redirect:/admin/tour_images";
            }
        } catch (Exception e) {
            redirect.addAttribute("msg", "Failed!!!");
            return "redirect:/admin/tour_images";
        }
    }

    @PostMapping("/admin/tour_images/delete")
    public String deleteTourImage(Integer id, RedirectAttributes redirect) throws ResourceNotFoundException {
        try {
            TourImage currentTourImage = tourImageRepository.findById(id).get();
            if (currentTourImage != null) {
                tourImageRepository.delete(currentTourImage);
                redirect.addAttribute("msg", "Delete success!!!");
                return "redirect:/admin/tour_images";
            } else {
                redirect.addAttribute("msg", "Delete failed!!!");
                return "redirect:/admin/tour_images";
            }
        } catch (Exception e) {
            redirect.addAttribute("msg", "Failed!!");
            return "redirect:/admin/tour_images";
        }
    }
}
