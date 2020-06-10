/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sem4.repository;

import com.example.sem4.model.Role;
import com.example.sem4.model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author July
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

  List<User> findByRoleId(Role roleId);

  Optional<User> findByEmail(String email);
}
