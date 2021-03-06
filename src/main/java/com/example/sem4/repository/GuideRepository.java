/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sem4.repository;

import com.example.sem4.model.Guide;
import com.example.sem4.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author MyPC
 */
public interface GuideRepository extends JpaRepository<Guide, Integer> {

  Optional<Guide> findByUserId(User user);
}
