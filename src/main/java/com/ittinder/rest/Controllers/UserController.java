package com.ittinder.rest.Controllers;

import com.ittinder.rest.Entities.User;
import com.ittinder.rest.Repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.events.Event;

import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
  //create user
  private final UserRepository userRepository;

  UserController(UserRepository repository){
    this.userRepository = repository;
  }

  //Creating a user
  @PostMapping( "/users")
  public ResponseEntity<HttpStatus> createUser(@RequestBody User newUser) {
    User user = newUser;
    String email = user.getEmail();

    if(userRepository.findByEmailIgnoreCase(email).isEmpty()) {
      userRepository.save(user);
      return ResponseEntity.ok(HttpStatus.CREATED);
    } else {
      return ResponseEntity.ok(HttpStatus.CONFLICT);
    }
  }

  //Get user by id
  @GetMapping("/users")
  public List<User> getUserById(@RequestParam(required = false) Long id) {
    List<User> users = new ArrayList<>();
    if (id == null) {
      users.addAll(userRepository.findAll());
    }
    else {
      users.addAll(userRepository.findUserById(id));
    }
    return users;
  }

}



