package com.ittinder.rest.Service;
import com.ittinder.rest.Entities.Session;
import com.ittinder.rest.Entities.User;
import com.ittinder.rest.Repositories.SessionRepository;
import com.ittinder.rest.Repositories.UserRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionService {
  private final UserRepository userRepository;
  private final SessionRepository sessionRepository;

  private static Long userId = (long)0;

  public SessionService(UserRepository userRepository, SessionRepository sessionRepository) {
    this.sessionRepository = sessionRepository;
    this.userRepository = userRepository;
  }

  public User getUserBySessionId(String sessionId) {
    //first grab session
    Session session = sessionRepository.getBySessionId(sessionId);
    //then grab user
    return session.getUser();
  }

  public User getUser() throws RuntimeException{
    if(userId == 0){

    }
    try {
      return userRepository.getById(userId);
    } catch (Exception e) {
      throw new RuntimeException("User with userid " + userId + " not defined.");
    }
  }
}
