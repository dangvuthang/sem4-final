/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sem4.repository;

import com.example.sem4.model.Tour;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author MyPC
 */
public interface TourRepository extends JpaRepository<Tour, Long> {
    
}
