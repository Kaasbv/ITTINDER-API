package com.ittinder.rest.Entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.OrderColumn;

@Entity
public class Chat {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  
  @ManyToOne
  private User iniatedUser;

  @ManyToOne
  private User affectedUser;

  @OneToMany(mappedBy = "chat", cascade = { CascadeType.ALL }, orphanRemoval = true)
  @OrderColumn(name = "id")
  private Message[] messages;

  public Chat(User iniatedUser, User affectedUser) {
    this.iniatedUser = iniatedUser;
    this.affectedUser = affectedUser;
  }

  public Chat(){}
}
