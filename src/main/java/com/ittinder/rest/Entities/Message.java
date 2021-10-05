package com.ittinder.rest.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Message {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  private String createdDate;

  private String message;

  @OneToOne
  private Chat chat;

  @OneToOne
  private User user;

  public Message(User user, Chat chat, String message) {
    this.user = user;
    this.chat = chat;
    this.message = message;
    // this.createdDate = new java.util.Date().toString();
  }

  public String getMessage() {
    return message;
  }

  public String getCreatedDate() {
    return createdDate;
  }
}
