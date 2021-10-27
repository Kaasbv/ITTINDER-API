package com.ittinder.rest.Service;
import com.ittinder.rest.Repositories.*;
import com.ittinder.rest.Entities.*;
import com.ittinder.rest.Service.SessionService;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  private final UserRepository userRepository;
  private final SessionService sessionService;

  public UserService(
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
    String email = user.getEmail();
    String gender = user.getGender();
    String interestedInGender = user.getInterestedInGender();
    String description = user.getDescription();

    user.setEmail(userDetails.getEmail());
    user.setGender(userDetails.getGender());
    user.setInterestedInGender(userDetails.getInterestedInGender());
    user.setDescription(userDetails.getDescription());

    //Prevent DB setting null values when user does not change all fiels
    if (user.getGender() == null) {
      user.setGender(gender);
    }

    if (user.getEmail() == null) {
      user.setEmail(email);
    }

    if (user.getInterestedInGender() == null) {
      user.setInterestedInGender(interestedInGender);
    }

    if (user.getDescription() == null) {
      user.setDescription(description);
    }

    userRepository.save(user);
  }

  public List<User> getUserStream(){
    User user = sessionService.getUser();
    return userRepository.findRandomUsers(user.getId(), PageRequest.of(0,10));
  }
}
