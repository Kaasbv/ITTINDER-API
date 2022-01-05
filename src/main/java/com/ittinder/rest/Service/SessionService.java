package com.ittinder.rest.Service;
import com.ittinder.rest.Entities.Session;
import com.ittinder.rest.Entities.User;
import com.ittinder.rest.Repositories.SessionRepository;
import com.ittinder.rest.Repositories.UserRepository;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
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

  public User getUser(HttpServletRequest request) throws RuntimeException{
    Long userId = request.getAttribute("logged_in_user_id") != null ? (Long) request.getAttribute("logged_in_user_id") : null;
    if(userId == null) return null;

    return userRepository.getById(userId);
  }
}
