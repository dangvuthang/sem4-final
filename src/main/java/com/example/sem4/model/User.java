/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sem4.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.Collection;
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
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

/**
 *
 * @author July
 */
@Entity
@Table(name = "users", uniqueConstraints = {
  @UniqueConstraint(columnNames = {"email"})
})
public class User {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @NotNull
  @Column(name = "email")
  private String email;

  @NotNull
  @Column(name = "password")
  private String password;

  @Column(name = "active", columnDefinition = "boolean default true")
  private Boolean active;

  @Column(name = "avatar_image")
  private String avatarImage;

  @NotNull
  @Column(name = "phone")
  private String phone;

  @NotNull
  @Column(name = "name")
  private String name;

  @JsonIgnore
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
  private Collection<Guide> guideCollection;

  @JsonIgnore
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
  private Collection<ReviewTour> reviewTourCollection;

  @JsonIgnore
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
  private Collection<ReviewGuide> reviewGuideCollection;

  @JsonIgnore
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
  private Collection<Booking> bookingCollection;

  @JsonIgnore
  @OneToMany(mappedBy = "userId")
  private Collection<AuthenticationProvider> authenticationProviderCollection;

  @JoinColumn(name = "role_id", referencedColumnName = "id")
  @ManyToOne(optional = false)
  private Role roleId;

  public User() {
  }

  public User(Long id) {
    this.id = id;
  }

  public User(Long id, String email, String password, String phone, String name) {
    this.id = id;
    this.email = email;
    this.password = password;
    this.phone = phone;
    this.name = name;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Boolean getActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }

  public String getAvatarImage() {
    return avatarImage;
  }

  public void setAvatarImage(String avatarImage) {
    this.avatarImage = avatarImage;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Collection<Guide> getGuideCollection() {
    return guideCollection;
  }

  public void setGuideCollection(Collection<Guide> guideCollection) {
    this.guideCollection = guideCollection;
  }

  public Collection<ReviewTour> getReviewTourCollection() {
    return reviewTourCollection;
  }

  public void setReviewTourCollection(Collection<ReviewTour> reviewTourCollection) {
    this.reviewTourCollection = reviewTourCollection;
  }

  public Collection<ReviewGuide> getReviewGuideCollection() {
    return reviewGuideCollection;
  }

  public void setReviewGuideCollection(Collection<ReviewGuide> reviewGuideCollection) {
    this.reviewGuideCollection = reviewGuideCollection;
  }

  public Collection<Booking> getBookingCollection() {
    return bookingCollection;
  }

  public void setBookingCollection(Collection<Booking> bookingCollection) {
    this.bookingCollection = bookingCollection;
  }

  public Collection<AuthenticationProvider> getAuthenticationProviderCollection() {
    return authenticationProviderCollection;
  }

  public void setAuthenticationProviderCollection(Collection<AuthenticationProvider> authenticationProviderCollection) {
    this.authenticationProviderCollection = authenticationProviderCollection;
  }

  public Role getRoleId() {
    return roleId;
  }

  public void setRoleId(Role roleId) {
    this.roleId = roleId;
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
    if (!(object instanceof User)) {
      return false;
    }
    User other = (User) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "model.User[ id=" + id + " ]";
  }

}
