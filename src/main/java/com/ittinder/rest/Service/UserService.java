package com.ittinder.rest.Service;
import com.ittinder.rest.Classes.UserWithSessionId;
import com.ittinder.rest.Repositories.*;
import com.ittinder.rest.Entities.*;
import com.ittinder.rest.Service.SessionService;

import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class UserService {
  private final UserRepository userRepository;
  private final SessionService sessionService;
  private final SessionRepository sessionRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public UserService(
    UserRepository userRepository,
    SessionService sessionService,
    SessionRepository sessionRepository
    ) {
    this.userRepository = userRepository;
    this.sessionService = sessionService;
    this.sessionRepository = sessionRepository;
  }

  public void createUser(User user) {
    System.out.println("Hashing user jonguh");
    user.setPassword(generateHash(user.getPassword()));
    userRepository.save(user);
  }

  public Boolean checkIfEmailExists(String email) {
    return !userRepository.findByEmailIgnoreCase(email).isEmpty();
  }
  
  public User getCurrentUser(HttpServletRequest request) {
    return sessionService.getUser(request);
  }


  public void updateUser(User userDetails, HttpServletRequest request) {
    //retrieves values of the user based on the current user
    User user = sessionService.getUser(request);
    String email = user.getEmail();
    String gender = user.getGender();
    String interestedInGender = user.getInterestedInGender();
    String description = user.getDescription();

    //assigns the input of the request body to the user
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

  public List<User> getUserStream(HttpServletRequest request) {
    User user = sessionService.getUser(request);
    return userRepository.findRandomUsers(user.getId(), PageRequest.of(0,1));
  }

  public String generateHash(String password) {
    return passwordEncoder.encode(password);
  }

  public UserWithSessionId login(String email, String password, HttpServletResponse response) throws Exception {
    User user = userRepository.findByEmailIgnoreCase(email).get(0);
    if (passwordEncoder.matches(password, user.getPassword())) {
      //Create session
      Session session = new Session(user);
      sessionRepository.save(session);
      Cookie cookie = new Cookie("session_id", session.getSessionId());
      response.addCookie(cookie);

      return new UserWithSessionId(user, session.getSessionId());
    }else{
      throw new Exception("Invalid email or password");
    }
  }
}
