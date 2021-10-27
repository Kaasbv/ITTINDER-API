package com.ittinder.rest.Service;
import com.ittinder.rest.Entities.User;
import com.ittinder.rest.Repositories.UserRepository;

import org.springframework.stereotype.Service;

@Service
public class SessionService {
  private final UserRepository userRepository;
  private static Long userId = (long)1;

  public SessionService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public User getUser() throws RuntimeException{
    try {
      return userRepository.getById(userId);
    } catch (Exception e) {
      throw new RuntimeException("User with userid " + userId + " not defined.");
    }
  }
}
