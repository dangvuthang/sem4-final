/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sem4.repository;

import com.example.sem4.dto.BookingDTO;
import com.example.sem4.model.Booking;
import com.example.sem4.model.User;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author MyPC
 */
public interface BookingRepository extends JpaRepository<Booking, Integer> {

    @Query("SELECT new com.example.sem4.dto.BookingDTO(t.name,t.price,t.priceDiscount,sum(b.quantity),sum(b.price)) FROM Booking b join Tour t"
            + " on b.tourId=t where b.createdAt between :from and :to group by t.name,t.price,t.priceDiscount order by sum(b.price) desc")
    List<BookingDTO> retrieveBookingAsDTO(@Param("from")Date from,@Param("to")Date to);

    @Query("SELECT new com.example.sem4.dto.BookingDTO(t.name,t.price,t.priceDiscount,sum(b.quantity),sum(b.price)) FROM Booking b join Tour t"
            + " on b.tourId=t  group by t.name,t.price,t.priceDiscount order by sum(b.price) desc")
    List<BookingDTO> retrieveBookingAsDTONoParams();
    List<Booking> findByUserId(User userId);
}
