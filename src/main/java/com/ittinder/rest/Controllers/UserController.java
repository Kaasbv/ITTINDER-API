package com.ittinder.rest.Controllers;

import com.ittinder.rest.Entities.Chat;
import com.ittinder.rest.Entities.User;
import com.ittinder.rest.Service.ChatService;
import com.ittinder.rest.Service.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/user")
public class UserController {
  private final ChatService chatService;
  private final UserService userService;

  UserController(ChatService chatService, UserService service) {
    this.chatService = chatService;
    this.userService = service;
  }

  //Creating a user
  @PostMapping
  public ResponseEntity<HttpStatus> createUser(@RequestBody User newUser) {
    String email = newUser.getEmail();

    if (!userService.checkIfEmailExists(email)) {
      userService.createUser(newUser);
      return ResponseEntity.ok(HttpStatus.CREATED);
    } else {
      return new ResponseEntity(HttpStatus.CONFLICT);
    }
  }

  //Get current logged in user by id
  @GetMapping
  public User getUser() {
    return userService.getCurrentUser();
  }

  //Update user info
  @PutMapping("/update")
  public ResponseEntity<HttpStatus> updateUser(@RequestBody User userDetails) {
    userService.updateUser(userDetails);
    return ResponseEntity.ok(HttpStatus.NO_CONTENT);
  }

  @ResponseBody
  @GetMapping("/stream")
  public List<User> getStream(){
    return this.userService.getUserStream();
  }

  @GetMapping("/chats")
  public List<Chat> getChatsFromUser(){
    return this.chatService.getChatsFromUser();
  }

  @PostMapping("Premium")
  public User setUserPremium() {return this.userService.setUserPremium();}
}

