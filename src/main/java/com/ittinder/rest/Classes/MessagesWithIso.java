package com.ittinder.rest.Classes;

import com.ittinder.rest.Entities.Message;

import java.util.List;

public class MessagesWithIso {
  public MessagesWithIso(List<Message> messages, String currentIso) {
    this.currentIso = currentIso;
    this.messages = messages;
  }

  public String currentIso;
  public List<Message> messages;
}
