/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sem4.exception;

/**
 *
 * @author July
 */
public class ResourceNotFoundException extends Exception {

  private static final long serialVersionUID = 1;

  public ResourceNotFoundException(String message) {
    super(message);
  }

}
