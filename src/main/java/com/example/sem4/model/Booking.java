/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sem4.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author July
 */
@Entity
@Table(name = "bookings")
public class Booking {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @NotNull
  @Column(name = "created_at")
  @Temporal(TemporalType.TIMESTAMP)
  private Date createdAt;

  @NotNull
  @Column(name = "price")
  private BigDecimal price;

  @Column(name = "paid")
  private Boolean paid;

  @NotNull
  @Column(name = "quantity")
  private Integer quantity;

  @Column(name = "start_date")
  @Temporal(TemporalType.DATE)
  private Date startDate;

  @Column(name = "end_date")
  @Temporal(TemporalType.DATE)
  private Date endDate;

  @JoinColumn(name = "tour_id", referencedColumnName = "id")
  @ManyToOne(optional = false)
  private Tour tourId;

  @JoinColumn(name = "user_id", referencedColumnName = "id")
  @ManyToOne(optional = false)
  private User userId;

  @NotNull
  @Column(name = "is_cancelled", columnDefinition = "boolean default true")
  private boolean isCancelled;

  public Booking() {
  }

  public Booking(Integer id) {
    this.id = id;
  }

  public Booking(Integer id, Date createdAt, BigDecimal price, Integer quantity, Date startDate, Date endDate) {
    this.id = id;
    this.createdAt = createdAt;
    this.price = price;
    this.quantity = quantity;
    this.startDate = startDate;
    this.endDate = endDate;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public Boolean getPaid() {
    return paid;
  }

  public void setPaid(Boolean paid) {
    this.paid = paid;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public Tour getTourId() {
    return tourId;
  }

  public void setTourId(Tour tourId) {
    this.tourId = tourId;
  }

  public User getUserId() {
    return userId;
  }

  public void setUserId(User userId) {
    this.userId = userId;
  }

  public boolean isIsCancelled() {
    return isCancelled;
  }

  public void setIsCancelled(boolean isCancelled) {
    this.isCancelled = isCancelled;
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

  
  
  @Override
  public int hashCode() {
    int hash = 0;
    hash += (id != null ? id.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Booking)) {
      return false;
    }
    Booking other = (Booking) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "model.Booking[ id=" + id + " ]";
  }

}
