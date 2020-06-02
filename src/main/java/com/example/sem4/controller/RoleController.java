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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class RoleController {

    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("admin/roles")
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @GetMapping("admin/roles/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable(name = "id") Long roleId) throws ResourceNotFoundException {
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new ResourceNotFoundException("Can not found Role with a given id: " + roleId));
        return ResponseEntity.ok(role);
    }

    @PostMapping("admin/roles/")
    public Role addUser(@RequestBody Role role) throws ResourceNotFoundException {
        return roleRepository.save(role);
    }

    @PutMapping("admin/roles/{id}")
    public ResponseEntity<Role> updateRoleById(@PathVariable(name = "id") Long roleId, @RequestBody Role role) throws ResourceNotFoundException {
        Role currentRole = roleRepository.findById(roleId).orElseThrow(() -> new ResourceNotFoundException("Can not found Role with a given id: " + roleId));
//    currentUser.setEmail(user.getEmail());
        currentRole.setName(role.getName());
        return ResponseEntity.ok(roleRepository.save(currentRole));
    }

    @DeleteMapping("admin/roles/{id}")
    public Map<String, Boolean> deleteRole(@PathVariable(name = "id") Long roleId) throws ResourceNotFoundException {
        Role currentRole = roleRepository.findById(roleId).orElseThrow(() -> new ResourceNotFoundException("Can not found Role with a given id: " + roleId));
        Map<String, Boolean> response = new HashMap<>();
        for (User user : userRepository.findAll()) {
            if (user.getRoleId()==currentRole) {
                response.put("deleted", Boolean.FALSE);
                return response;
            }
        }
        roleRepository.delete(currentRole);
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
