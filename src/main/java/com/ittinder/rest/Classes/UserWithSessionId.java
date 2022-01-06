package com.ittinder.rest.Classes;

import com.ittinder.rest.Entities.User;

public class UserWithSessionId {
  public UserWithSessionId(User user, String sessionId) {
    this.user = user;
    this.sessionId = sessionId;
  }

  public String sessionId;
  public User user;
}
