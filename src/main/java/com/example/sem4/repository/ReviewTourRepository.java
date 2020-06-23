/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sem4.repository;

import com.example.sem4.model.ReviewTour;
import com.example.sem4.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author MyPC
 */
public interface ReviewTourRepository extends JpaRepository<ReviewTour, Integer> {

  List<ReviewTour> findByUserId(User user);
}
