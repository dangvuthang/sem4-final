/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sem4.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "tour_locations")
public class TourLocation {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @NotNull
  @Column(name = "date")
  @Temporal(TemporalType.TIMESTAMP)
  private Date date;

  @JoinColumn(name = "location_id", referencedColumnName = "id")
  @ManyToOne(optional = false)
  private Location locationId;

  @JoinColumn(name = "tour_id", referencedColumnName = "id")
  @ManyToOne(optional = false)
  private Tour tourId;

  public TourLocation() {
  }

  public TourLocation(Long id) {
    this.id = id;
  }

  public TourLocation(Long id, Date date) {
    this.id = id;
    this.date = date;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public Location getLocationId() {
    return locationId;
  }

  public void setLocationId(Location locationId) {
    this.locationId = locationId;
  }

  public Tour getTourId() {
    return tourId;
  }

  public void setTourId(Tour tourId) {
    this.tourId = tourId;
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
    if (!(object instanceof TourLocation)) {
      return false;
    }
    TourLocation other = (TourLocation) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "model.TourLocation[ id=" + id + " ]";
  }

}
