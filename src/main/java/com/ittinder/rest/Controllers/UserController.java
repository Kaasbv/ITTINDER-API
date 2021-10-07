package com.ittinder.rest.Controllers;

import com.ittinder.rest.Entities.User;
import com.ittinder.rest.Repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
  //create user
  private final UserRepository userRepository;

  UserController(UserRepository repository){
    this.userRepository = repository;
  }

  //Postmapping multiple values from constructor in User class
  @PostMapping( "/user")
  public ResponseEntity<HttpStatus> createUser(@RequestBody User newUser) {
    User user = newUser;
    userRepository.save(user);
    return ResponseEntity.ok(HttpStatus.OK);
  }

  @GetMapping()
  public void getUser() {

  }
}

