/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sem4.model;

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
@Table(name = "review_guides")

public class ReviewGuide {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @NotNull
  @Column(name = "review")
  private String review;

  @NotNull
  @Column(name = "rating")
  private int rating;

  @NotNull
  @Column(name = "created_at")
  @Temporal(TemporalType.TIMESTAMP)
  private Date createdAt;
  
  @JoinColumn(name = "guide_id", referencedColumnName = "id")
  @ManyToOne(optional = false)
  private Guide guideId;
  
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  @ManyToOne(optional = false)
  private User userId;

  public ReviewGuide() {
  }

  public ReviewGuide(Long id) {
    this.id = id;
  }

  public ReviewGuide(Long id, String review, int rating, Date createdAt) {
    this.id = id;
    this.review = review;
    this.rating = rating;
    this.createdAt = createdAt;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getReview() {
    return review;
  }

  public void setReview(String review) {
    this.review = review;
  }

  public int getRating() {
    return rating;
  }

  public void setRating(int rating) {
    this.rating = rating;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public Guide getGuideId() {
    return guideId;
  }

  public void setGuideId(Guide guideId) {
    this.guideId = guideId;
  }

  public User getUserId() {
    return userId;
  }

  public void setUserId(User userId) {
    this.userId = userId;
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
    if (!(object instanceof ReviewGuide)) {
      return false;
    }
    ReviewGuide other = (ReviewGuide) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "model.ReviewGuide[ id=" + id + " ]";
  }

}
