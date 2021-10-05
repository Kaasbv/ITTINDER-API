package com.ittinder.rest.Controllers;

import com.ittinder.rest.Entities.User;
import com.ittinder.rest.Repositories.UserRepository;
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

  //Postmapping met meerdere values is me nog niet gelukt helaas...
  @PostMapping("/user/registration")
  public String createUser(@RequestBody String name) {
    User newUser = new User(name);
    userRepository.save(newUser);
    return "Registered user: " + name;
  }
}

