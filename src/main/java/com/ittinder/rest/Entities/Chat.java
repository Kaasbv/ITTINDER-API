package com.ittinder.rest.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@lombok.Getter
@lombok.Setter
public class Chat {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @ManyToOne
  private User iniatedUser;

  @ManyToOne
  private User affectedUser;

  @JsonIgnore
  @OneToMany(mappedBy = "chat", cascade = { CascadeType.ALL }, orphanRemoval = true)
  @OrderColumn(name = "id")
  private Message[] messages;

  public Chat(User iniatedUser, User affectedUser) {
    this.iniatedUser = iniatedUser;
    this.affectedUser = affectedUser;
  }

  public Chat(){}
}
