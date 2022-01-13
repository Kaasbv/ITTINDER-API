package com.ittinder.rest.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ManyToAny;

import java.time.Instant;
import java.util.Date;

@Entity
@Getter
@Setter
public class Message {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  private String createdDate;

  private String message;


  @ManyToOne
  private Chat chat;

  @ManyToOne
  private User user;

  public Message(User user, Chat chat, String message) {
    this.user = user;
    this.chat = chat;
    this.message = message;
    this.createdDate = Instant.now().toString();
  }

  public Message(){}
}
