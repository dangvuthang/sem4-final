/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sem4.dto;

import com.example.sem4.model.Tour;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 *
 * @author vu378
 */
public class BookingDTO {

    private Long quantity;
    private BigDecimal price;
    private BigInteger priceTour;
    private BigInteger discount;
    private String tourName;
    private Tour tour;
    private BigDecimal total;
    private Date startDate,endDate;

    public BookingDTO(String tourName, BigInteger priceTour, BigInteger discount, Long quantity, BigDecimal total) {
        this.tourName = tourName;
        this.priceTour = priceTour;
        this.discount=discount;
        this.quantity = quantity;
        this.total = total;
    }
    
    public BookingDTO(String tourName, BigDecimal priceTour, BigInteger discount, Long quantity, BigDecimal total) {
        this.tourName = tourName;
        this.price = priceTour;
        this.discount=discount;
        this.quantity = quantity;
        this.total = total;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Tour getTour() {
        return tour;
    }

    public BigInteger getDiscount() {
        return discount;
    }

    public void setDiscount(BigInteger discount) {
        this.discount = discount;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public BigInteger getPriceTour() {
        return priceTour;
    }

    public void setPriceTour(BigInteger priceTour) {
        this.priceTour = priceTour;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getTourName() {
        return tourName;
    }

    public void setTourName(String tourName) {
        this.tourName = tourName;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

}
