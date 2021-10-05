package com.ittinder.rest.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderColumn;

@Entity
public class Chat {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  
  @OneToOne
  private User iniatedUser;

  @OneToOne
  private User affectedUser;

  @OneToMany
  @JoinColumn(name = "chat_id")
  @OrderColumn(name = "id")
  private Message[] messages;

  protected Chat(User iniatedUser, User affectedUser) {
    this.iniatedUser = iniatedUser;
    this.affectedUser = affectedUser;
  }
}
