package com.ittinder.rest.Entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@DiscriminatorValue("premium")
public class PremiumUser extends BaseUser {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  
  public PremiumUser(){
  }

  public String getType() {
      return "premium";
  }
}
