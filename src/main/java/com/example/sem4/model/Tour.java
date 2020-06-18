/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sem4.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author July
 */
@Entity
@Table(name = "tours")
public class Tour {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @NotNull
  @Column(name = "name")
  private String name;

  @NotNull
  @Column(name = "start_date")
  @Temporal(TemporalType.DATE)
  private Date startDate;

  @NotNull
  @Column(name = "end_date")
  @Temporal(TemporalType.DATE)
  private Date endDate;

  @NotNull
  @Column(name = "duration")
  private int duration;

  @NotNull
  @Column(name = "max_group_size")
  private int maxGroupSize;

  @NotNull
  @Column(name = "current_group_size")
  private int currentGroupSize;

  @NotNull
  @Column(name = "price")
  private BigInteger price;

  @Column(name = "price_discount")
  private BigInteger priceDiscount;

  @NotNull
  @Column(name = "summary")
  private String summary;

  @NotNull
  @Column(name = "description", columnDefinition = "TEXT")
  private String description;

  @NotNull
  @Column(name = "active", columnDefinition = "boolean default true")
  private boolean active;

  @Column(name = "tour_image_cover")
  private String tourImageCover;

  @Column(name = "rating_average")
  private BigDecimal ratingAverage;

  @Column(name = "number_of_ratings")
  private BigInteger numberOfRatings;

  @JsonManagedReference
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "tourId")
  private Collection<ReviewTour> reviewTourCollection;

  @JsonManagedReference
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "tourId")
  private Collection<TourLocation> tourLocationCollection;

  @JsonIgnore
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "tourId")
  private Collection<Booking> bookingCollection;

  @JoinColumn(name = "guide_id", referencedColumnName = "id")
  @ManyToOne
  private Guide guideId;

  @JoinColumn(name = "tour_type_id", referencedColumnName = "id")
  @ManyToOne
  private TourType tourTypeId;

  @JsonIgnore
  @OneToMany(mappedBy = "tourId")
  private Collection<TourImage> tourImageCollection;

  public Tour() {
  }

  public Tour(Integer id) {
    this.id = id;
  }

  public Tour(Integer id, String name, Date startDate, Date endDate, int duration, int maxGroupSize, BigInteger price, String summary, String description, boolean active, int currentGroupSize) {
    this.id = id;
    this.name = name;
    this.startDate = startDate;
    this.endDate = endDate;
    this.duration = duration;
    this.maxGroupSize = maxGroupSize;
    this.price = price;
    this.summary = summary;
    this.description = description;
    this.active = active;
    this.currentGroupSize = currentGroupSize;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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

  public int getDuration() {
    return duration;
  }

  public void setDuration(int duration) {
    this.duration = duration;
  }

  public int getMaxGroupSize() {
    return maxGroupSize;
  }

  public void setMaxGroupSize(int maxGroupSize) {
    this.maxGroupSize = maxGroupSize;
  }

  public BigInteger getPrice() {
    return price;
  }

  public void setPrice(BigInteger price) {
    this.price = price;
  }

  public BigInteger getPriceDiscount() {
    return priceDiscount;
  }

  public void setPriceDiscount(BigInteger priceDiscount) {
    this.priceDiscount = priceDiscount;
  }

  public String getSummary() {
    return summary;
  }

  public void setSummary(String summary) {
    this.summary = summary;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public boolean getActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public String getTourImageCover() {
    return tourImageCover;
  }

  public void setTourImageCover(String tourImageCover) {
    this.tourImageCover = tourImageCover;
  }

  public BigDecimal getRatingAverage() {
    return ratingAverage;
  }

  public void setRatingAverage(BigDecimal ratingAverage) {
    this.ratingAverage = ratingAverage;
  }

  public BigInteger getNumberOfRatings() {
    return numberOfRatings;
  }

  public void setNumberOfRatings(BigInteger numberOfRatings) {
    this.numberOfRatings = numberOfRatings;
  }

  public Collection<ReviewTour> getReviewTourCollection() {
    return reviewTourCollection;
  }

  public void setReviewTourCollection(Collection<ReviewTour> reviewTourCollection) {
    this.reviewTourCollection = reviewTourCollection;
  }

  public Collection<TourLocation> getTourLocationCollection() {
    return tourLocationCollection;
  }

  public void setTourLocationCollection(Collection<TourLocation> tourLocationCollection) {
    this.tourLocationCollection = tourLocationCollection;
  }

  public Collection<Booking> getBookingCollection() {
    return bookingCollection;
  }

  public void setBookingCollection(Collection<Booking> bookingCollection) {
    this.bookingCollection = bookingCollection;
  }

  public Guide getGuideId() {
    return guideId;
  }

  public void setGuideId(Guide guideId) {
    this.guideId = guideId;
  }

  public TourType getTourTypeId() {
    return tourTypeId;
  }

  public void setTourTypeId(TourType tourTypeId) {
    this.tourTypeId = tourTypeId;
  }

  public Collection<TourImage> getTourImageCollection() {
    return tourImageCollection;
  }

  public void setTourImageCollection(Collection<TourImage> tourImageCollection) {
    this.tourImageCollection = tourImageCollection;
  }

  public int getCurrentGroupSize() {
    return currentGroupSize;
  }

  public void setCurrentGroupSize(int currentGroupSize) {
    this.currentGroupSize = currentGroupSize;
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
    if (!(object instanceof Tour)) {
      return false;
    }
    Tour other = (Tour) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "model.Tour[ id=" + id + " ]";
  }

}
