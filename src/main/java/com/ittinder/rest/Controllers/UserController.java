package com.ittinder.rest.Controllers;

import ch.qos.logback.core.joran.conditional.ElseAction;

import com.ittinder.rest.Entities.Chat;
import com.ittinder.rest.Entities.User;
import com.ittinder.rest.Entities.preMatch;
import com.ittinder.rest.Repositories.UserRepository;
import com.ittinder.rest.Service.UserService;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
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
@RequestMapping("/user")
public class UserController {
  private final UserRepository userRepository;
  private final UserService userService;

  UserController(UserRepository repository, UserService service) {
    this.userRepository = repository;
    this.userService = service;
  }

  //Creating a user
  @PostMapping
  public ResponseEntity<HttpStatus> createUser(@RequestBody User newUser) {
    String email = newUser.getEmail();

    if (!userService.checkIfEmailExists(email)) {
      userRepository.save(newUser);
      return ResponseEntity.ok(HttpStatus.CREATED);
    } else {
      return ResponseEntity.ok(HttpStatus.CONFLICT);
    }
  }

  //Get current logged in user by id
  @GetMapping
  public User getUser() {
    return userService.getCurrentUser();
  }


  //Update user info
  @PutMapping("/update")
  public ResponseEntity<HttpStatus> updateUser(@PathVariable(value = "id") Long id, @RequestBody User userDetails) {
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

  @ResponseBody
  @GetMapping("/stream")
  public List<User> getRandomUsers(@RequestParam Integer id){
    List<User> randomUsers = new ArrayList<>();

    randomUsers.addAll(userRepository.findRandomUsers(id, PageRequest.of(0,10)));

    return  randomUsers;
  }

  @GetMapping("/chats")
  public List<Chat> getAll(@PathVariable Integer initiatedUser){
    List<Chat> foundChats = new ArrayList<>();
    return 
    else {
      foundChats.addAll(chatRepository.findChatByAffectedUserId(initiatedUser));
    }
    return foundChats;
  }






}

