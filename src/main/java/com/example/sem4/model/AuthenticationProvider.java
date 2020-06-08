/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sem4.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author July
 */
@Entity
@Table(name = "authentication_providers")
public class AuthenticationProvider {

  @Id
  @NotNull
  private String providerKey;

  @JoinColumn(name = "user_id", referencedColumnName = "id")
  @ManyToOne
  private User userId;

  public AuthenticationProvider() {
  }

  public AuthenticationProvider(String providerKey) {
    this.providerKey = providerKey;
  }

  public String getProviderKey() {
    return providerKey;
  }

  public void setProviderKey(String providerKey) {
    this.providerKey = providerKey;
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
    hash += (providerKey != null ? providerKey.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof AuthenticationProvider)) {
      return false;
    }
    AuthenticationProvider other = (AuthenticationProvider) object;
    if ((this.providerKey == null && other.providerKey != null) || (this.providerKey != null && !this.providerKey.equals(other.providerKey))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "model.AuthenticationProvider[ providerKey=" + providerKey + " ]";
  }

}
