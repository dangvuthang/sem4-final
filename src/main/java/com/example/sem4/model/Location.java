/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sem4.model;

import java.math.BigInteger;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author July
 */
@Entity
@Table(name = "locations")

public class Location  {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @NotNull
  @Column(name = "name")
  private String name;

  @NotNull
  @Column(name = "latitude")
  private BigInteger latitude;

  @NotNull
  @Column(name = "longitude")
  private BigInteger longitude;

  @NotNull
  @Column(name = "address")
  private String address;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "locationId")
  private Collection<TourLocation> tourLocationCollection;

  public Location() {
  }

  public Location(Long id) {
    this.id = id;
  }

  public Location(Long id, String name, BigInteger latitude, BigInteger longitude, String address) {
    this.id = id;
    this.name = name;
    this.latitude = latitude;
    this.longitude = longitude;
    this.address = address;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public BigInteger getLatitude() {
    return latitude;
  }

  public void setLatitude(BigInteger latitude) {
    this.latitude = latitude;
  }

  public BigInteger getLongitude() {
    return longitude;
  }

  public void setLongitude(BigInteger longitude) {
    this.longitude = longitude;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public Collection<TourLocation> getTourLocationCollection() {
    return tourLocationCollection;
  }

  public void setTourLocationCollection(Collection<TourLocation> tourLocationCollection) {
    this.tourLocationCollection = tourLocationCollection;
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
    if (!(object instanceof Location)) {
      return false;
    }
    Location other = (Location) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "model.Location[ id=" + id + " ]";
  }
  
}
