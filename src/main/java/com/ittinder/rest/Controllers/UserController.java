package com.ittinder.rest.Controllers;

import ch.qos.logback.core.joran.conditional.ElseAction;
import com.ittinder.rest.Entities.User;
import com.ittinder.rest.Repositories.UserRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.events.Event;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.websocket.server.PathParam;
import java.util.*;

@RestController
public class UserController {
  private final UserRepository userRepository;

  UserController(UserRepository repository) {
    this.userRepository = repository;
  }

  //Creating a user
  @PostMapping("/user")
  public ResponseEntity<HttpStatus> createUser(@RequestBody User newUser) {
    User user = newUser;
    String email = user.getEmail();

    if (userRepository.findByEmailIgnoreCase(email).isEmpty()) {
      userRepository.save(user);
      return ResponseEntity.ok(HttpStatus.CREATED);
    } else {
      return ResponseEntity.ok(HttpStatus.CONFLICT);
    }
  }

  //Get one user by id
  @GetMapping("/user/{id}")
  public User getUserById(@PathVariable Long id) {
    return userRepository.getUserById(id);
  }

  //Get all users
  @GetMapping("/users")
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  //Update user info
  @PutMapping("/user/{id}/update")
  public ResponseEntity<HttpStatus> updateUser(@PathVariable(value = "id") Long id,
                                               @RequestBody User userDetails) {
    User user = userRepository.getUserById(id);
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
    return ResponseEntity.ok(HttpStatus.NO_CONTENT);
  }
}




