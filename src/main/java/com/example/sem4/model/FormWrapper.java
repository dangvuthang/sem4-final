/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sem4.model;

import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author July
 */
public class FormWrapper {

  private MultipartFile avatarImage;
  private String email;
  private String password;
  private String phone;
  private String name;

  public FormWrapper() {
  }

  public FormWrapper(MultipartFile avatarImage, String email, String password, String phone, String name) {
    this.avatarImage = avatarImage;
    this.email = email;
    this.password = password;
    this.phone = phone;
    this.name = name;
  }

  public MultipartFile getAvatarImage() {
    return avatarImage;
  }

  public void setAvatarImage(MultipartFile avatarImage) {
    this.avatarImage = avatarImage;
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

}
