/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sem4.controller;

import com.example.sem4.exception.ResourceNotFoundException;
import com.example.sem4.model.Booking;
import com.example.sem4.model.Guide;
import com.example.sem4.model.Location;
import com.example.sem4.model.User;
import com.example.sem4.repository.BookingRepository;
import com.example.sem4.repository.GuideRepository;
import com.example.sem4.repository.UserRepository;
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

public class GuideViewController {
    @Autowired
    private GuideRepository guideRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private BookingRepository bookingRepository;
    
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/admin/guides")
    public String getAllTourType(HttpServletRequest request, ModelMap model, Model attr) throws URISyntaxException {
        if (request.getSession().getAttribute("username") == null) {
           return "redirect:/admin";
        } else {
           model.addAttribute("user", request.getSession().getAttribute("username"));
           model.addAttribute("list", guideRepository.findAll());
         String msg = (String) attr.asMap().get("msg");
         model.addAttribute("msg", msg);
            return "list_guides";
        }
    }

    @GetMapping("/admin/guides/{id}")
    public ResponseEntity<Guide> getGuideById(@PathVariable(name = "id") Integer guideId) throws ResourceNotFoundException {
        Guide guide = guideRepository.findById(guideId).orElseThrow(() -> new ResourceNotFoundException("Can not found Guide with a given id: " + guideId));
        return ResponseEntity.ok(guide);
    }
    
    @PostMapping("admin/guides/")
    public Guide addGuide(@RequestBody Guide guide) throws ResourceNotFoundException {
        return guideRepository.save(guide);
    }

    @PutMapping("admin/guides/{id}")
    public ResponseEntity<Guide> updateGuideById(@PathVariable(name = "id") Integer guideId, @RequestBody Guide guide) throws ResourceNotFoundException {
        Guide currentGuide = guideRepository.findById(guideId).orElseThrow(() -> new ResourceNotFoundException("Can not found Guide with a given id: " + guideId));
        currentGuide.setDescription(guide.getDescription());
        return ResponseEntity.ok(guideRepository.save(currentGuide));
    }

    @DeleteMapping("admin/guides/{id}")
    public Map<String, Boolean> deleteGuide(@PathVariable(name = "id") Integer guideId) throws ResourceNotFoundException {
        Guide currentGuide = guideRepository.findById(guideId).orElseThrow(() -> new ResourceNotFoundException("Can not found Guide with a given id: " + guideId));
        Map<String, Boolean> response = new HashMap<>();
        for (User user : userRepository.findAll()) {
            if (user.getGuideCollection().contains(currentGuide)) {
                response.put("deleted", Boolean.FALSE);
                return response;
            }
        }
        guideRepository.delete(currentGuide);
        response.put("deleted", Boolean.TRUE);
        return response;
    }
 //******************************************MODIFY*********************************************//
    @PostMapping("admin/guides/delete")
    public String deleteGuide(Integer id, RedirectAttributes redirect) throws ResourceNotFoundException {
        Guide currentGuide = guideRepository.findById(id).get();
        try {
            if (currentGuide != null) {
//                if (currentGuide.IsIsGuide()) {
//                    for (Booking booking : bookingRepository.findAll()) {
//                        if (booking.getUserId().getId() == currentGuide.getUserId().getId()) {
//                            redirect.addFlashAttribute("msg", "Can't deactive!!!");
//                            return "redirect:/admin/guides";
//                        }
                guideRepository.delete(currentGuide);
                redirect.addFlashAttribute("msg", "Delete success!!!");
                return "redirect:/admin/guides";
            } else {
                redirect.addFlashAttribute("msg", "Delete failed!!!");
                return "redirect:/admin/guides";
            }
        } catch (Exception e) {
            redirect.addFlashAttribute("msg", "Failed!!!");
            return "redirect:/admin/guides";
        }

    }
}
