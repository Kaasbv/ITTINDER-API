package com.ittinder.rest.Controllers;

import com.ittinder.rest.Entities.User;
import com.ittinder.rest.Entities.preMatch;
import com.ittinder.rest.Repositories.UserRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


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

  @GetMapping("/getUsers")
  public List<User> getAll(@RequestParam(required = false) Integer id){
    List<User> users = new ArrayList<>();

    if (id == 0)  {
      users.addAll(userRepository.findAll());
    }
    else {
      users.addAll(userRepository.findUserById(id));
    }
    return users;
  }

  @ResponseBody
  @GetMapping("/randomUserStream")
  public List<User> getRandomUsers(@RequestParam Integer id){
    List<User> randomUsers = new ArrayList<>();

    randomUsers.addAll(userRepository.findRandomUsers(id, PageRequest.of(0,10)));

    return  randomUsers;
  }







}

