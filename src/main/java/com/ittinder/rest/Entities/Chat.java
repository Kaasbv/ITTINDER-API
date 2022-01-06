package com.ittinder.rest.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@lombok.Getter
@lombok.Setter
public class Chat {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @ManyToOne
  private User initiatedUser;

  @ManyToOne
  private User affectedUser;

  @JsonIgnore
  @OneToMany(mappedBy = "chat", cascade = { CascadeType.ALL }, orphanRemoval = true)
  @OrderColumn(name = "id")
  private Message[] messages;

  @Transient
  private Message lastMessage;

  public Chat(User initiatedUser, User affectedUser) {
    this.initiatedUser = initiatedUser;
    this.affectedUser = affectedUser;
  }

  public Chat(){}
}
