package com.ittinder.rest.Service;
import com.ittinder.rest.Repositories.*;
import com.ittinder.rest.Entities.*;
import com.ittinder.rest.Service.SessionService;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class UserService {
  private final UserRepository userRepository;
  private final SessionService sessionService;

  public ChatService(
    UserRepository userRepository,
    SessionService sessionService
    ) {
    this.userRepository = userRepository;
    this.sessionService = sessionService;
  }

  public void createUser(User user) {
    userRepository.save(user);
  }

  public Boolean checkIfEmailExists(String email) {
    return !userRepository.findByEmailIgnoreCase(email).isEmpty();
  }
  
  public User getCurrentUser() {
    return sessionService.getUser();
  }

  public void updateUser(User userDetails) {
    User user = sessionService.getUser();
    
    userRepository.save(user);
  }
}
