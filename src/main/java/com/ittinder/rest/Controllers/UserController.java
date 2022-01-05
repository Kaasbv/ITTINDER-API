package com.ittinder.rest.Controllers;

import com.ittinder.rest.Entities.Chat;
import com.ittinder.rest.Entities.User;
import com.ittinder.rest.Service.ChatService;
import com.ittinder.rest.Service.UserService;

import org.hibernate.Hibernate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
      System.out.println("Creating user");

      userService.createUser(newUser);
      return ResponseEntity.ok(HttpStatus.CREATED);
    } else {
      return new ResponseEntity(HttpStatus.CONFLICT);
    }
  }

  //Get current logged in user by id
  @GetMapping
  public User getUser(HttpServletRequest request) {
    return userService.getCurrentUser(request);
  }

  //Update user info
  @PutMapping("/update")
  public ResponseEntity<HttpStatus> updateUser(@RequestBody User userDetails, HttpServletRequest request) {
    userService.updateUser(userDetails, request);
    return ResponseEntity.ok(HttpStatus.NO_CONTENT);
  }

  @ResponseBody
  @GetMapping("/stream")
  public List<User> getStream(HttpServletRequest request) {
    return this.userService.getUserStream(request);
  }

  @GetMapping("/chats")
  public List<Chat> getChatsFromUser(HttpServletRequest request) {
    return this.chatService.getChatsFromUser(request);
  }

  @PostMapping("/login")
  public ResponseEntity<User> login(@RequestBody User user, HttpServletResponse response) {
    try {
      return ResponseEntity.ok(userService.login(
          user.getEmail(),
          user.getPassword(),
          response
      ));
    }catch(Exception e) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
  }
}

