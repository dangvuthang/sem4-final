/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sem4.controller;

import com.example.sem4.exception.ResourceNotFoundException;
import com.example.sem4.model.Role;
import com.example.sem4.model.User;
import com.example.sem4.repository.RoleRepository;
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

public class RoleViewController {

    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/admin/roles")
    public String getAllTourType(HttpServletRequest request, ModelMap model, Model attr) throws URISyntaxException {
        if (request.getSession().getAttribute("username") == null) {
            return "redirect:/admin";
        } else {
            model.addAttribute("user", request.getSession().getAttribute("username"));
            model.addAttribute("list", roleRepository.findAll());
            String msg = (String) attr.asMap().get("msg");
            model.addAttribute("msg", msg);
            return "list_roles";
        }
    }

    @GetMapping("admin/roles/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable(name = "id") Integer roleId) throws ResourceNotFoundException {
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new ResourceNotFoundException("Can not found Role with a given id: " + roleId));
        return ResponseEntity.ok(role);
    }
 //******************************************MODIFY*********************************************//
    @PostMapping("/admin/roles")
    public String addRole(Role role, RedirectAttributes redirect ) throws ResourceNotFoundException {
         try {
            for (Role r : roleRepository.findAll()) {
                if (r.getName().toLowerCase().equals(role.getName().toLowerCase())) {
                    redirect.addFlashAttribute("msg", "Location with name '" + role.getName() + "' existed!!!");
                    return "redirect:/admin/roles";
                }
            }
            if (roleRepository.save(role) != null) {
                redirect.addFlashAttribute("msg", "Add success!!!");
                return "redirect:/admin/roles";
            } else {
                redirect.addFlashAttribute("msg", "Add failed!!!");
                return "redirect:/admin/role";
            }
        } catch (Exception e) {
            redirect.addFlashAttribute("msg", "Failed!!!");
            return "redirect:/admin/role";
        }
    }

    @PostMapping("/admin/roles/update")
    public String updateRoleById(Role role, RedirectAttributes redirect) throws ResourceNotFoundException {
        try {
            Role currentRole = roleRepository.findById(role.getId()).get();
            currentRole.setName(role.getName());
            
            if (roleRepository.save(role) != null) {
                redirect.addFlashAttribute("msg", "Update success!!!");
                return "redirect:/admin/roles";
            } else {
                redirect.addFlashAttribute("msg", "Update failed!!!");
                return "redirect:/admin/roles";
            }
        } catch (Exception e) {
            redirect.addFlashAttribute("msg", "Failed!!!");
            return "redirect:/admin/roles";
        }
    }

    @PostMapping("/admin/roles/delete")
    public String deleteRole(Integer id, RedirectAttributes redirect) throws ResourceNotFoundException {
        try {
            Role currentRole = roleRepository.findById(id).get();
            if (currentRole != null) {
                roleRepository.delete(currentRole);
                redirect.addAttribute("msg", "Delete success!!!");
                return "redirect:/admin/roles";
            } else {
                redirect.addAttribute("msg", "Delete failed!!!");
                return "redirect:/admin/roles";
            }
        } catch (Exception e) {
            redirect.addAttribute("msg", "Failed!!");
            return "redirect:/admin/roles";
        }
    }
}
