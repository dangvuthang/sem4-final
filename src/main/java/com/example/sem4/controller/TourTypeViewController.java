/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sem4.controller;

import com.example.sem4.exception.ResourceNotFoundException;
import com.example.sem4.model.Tour;
import com.example.sem4.model.TourType;
import com.example.sem4.repository.TourRepository;
import com.example.sem4.repository.TourTypeRepository;
import com.example.sem4.util.JwtUtil;
import java.net.URISyntaxException;
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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TourTypeViewController {
    @Autowired
    private TourTypeRepository tourTypeRepository;
    
    @Autowired
    private TourRepository tourRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/admin/tour_types")
    public String getAllTourType(HttpServletRequest request, ModelMap model, Model attr) throws URISyntaxException {
        if (request.getSession().getAttribute("username") == null) {
           return "redirect:/admin";
        } else {
           model.addAttribute("user", request.getSession().getAttribute("username"));
           model.addAttribute("list", tourTypeRepository.findAll());
         String msg = (String) attr.asMap().get("msg");
         model.addAttribute("msg", msg);
            return "list_tour_types";
        }
    }

    @GetMapping("/admin/tour_types/{id}")
    public ResponseEntity<TourType> getTourTypeById(@PathVariable(name = "id") Integer tourTypeId) throws ResourceNotFoundException {
        TourType tourType = tourTypeRepository.findById(tourTypeId).orElseThrow(() -> new ResourceNotFoundException("Can not found Tour Type with a given id: " + tourTypeId));
        return ResponseEntity.ok(tourType);
    }
     //******************************************MODIFY*********************************************//
    @PostMapping("/admin/tour_types")
    public String addTourType(TourType tourType, RedirectAttributes redirect ) throws ResourceNotFoundException {
        try {
           for (TourType ty : tourTypeRepository.findAll()) {
                if (ty.getName().toLowerCase().equals(tourType.getName().toLowerCase())) {
                    redirect.addFlashAttribute("msg", "Location with name '" + tourType.getName() + "' existed!!!");
                    return "redirect:/admin/tours";
                }
            }
            if (tourTypeRepository.save(tourType) != null) {
                redirect.addFlashAttribute("msg", "Add success!!!");
                return "redirect:/admin/tour_types";
            } else {
                redirect.addFlashAttribute("msg", "Add failed!!!");
                return "redirect:/admin/tour_types";
            }
        } catch (Exception e) {
            redirect.addFlashAttribute("msg", "Failed!!!");
            return "redirect:/admin/tour_types";
        }
    }
    

    @PostMapping("/admin/tour_types/update")
    public String updateTourTypeById(TourType tourType, RedirectAttributes redirect) throws ResourceNotFoundException {
        try {
            TourType currentTourType = tourTypeRepository.findById(tourType.getId()).get();
            currentTourType.setName(tourType.getName());
            
           if (tourTypeRepository.save(tourType) != null) {
                redirect.addFlashAttribute("msg", "Update success!!!");
                return "redirect:/admin/tour_types";
            } else {
                redirect.addFlashAttribute("msg", "Update failed!!!");
                return "redirect:/admin/tour_types";
            }
        } catch (Exception e) {
            redirect.addFlashAttribute("msg", "Failed!!!");
            return "redirect:/admin/tour_types";
        }
    }

   @PostMapping("/admin/tour_types/delete")
    public String deleteTourType(Integer id, RedirectAttributes redirect) throws ResourceNotFoundException {
        try {
            TourType currentTourType = tourTypeRepository.findById(id).get();
            if (currentTourType != null) {
                tourTypeRepository.delete(currentTourType);
                redirect.addAttribute("msg", "Delete success!!!");
                return "redirect:/admin/tour_types";
            } else {
                redirect.addAttribute("msg", "Delete failed!!!");
               return "redirect:/admin/tour_types";
            }
        } catch (Exception e) {
            redirect.addAttribute("msg", "Failed!!");
            return "redirect:/admin/tour_types";
        }
 }
}
