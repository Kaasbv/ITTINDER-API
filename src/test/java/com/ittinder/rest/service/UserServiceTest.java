package com.ittinder.rest.service;

import org.hibernate.mapping.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import com.ittinder.rest.Repositories.UserRepository;
import com.ittinder.rest.Entities.User;
import com.ittinder.rest.Service.SessionService;
import com.ittinder.rest.Service.UserService;

public class UserServiceTest {
  private UserService sut;
  private UserRepository userRepository;
  private SessionService sessionService;

  @BeforeEach
  public void beforeEach() {
    userRepository = mock(UserRepository.class);
    sessionService = mock(SessionService.class);

    User user = mock(User.class);
    when(sessionService.getUser()).thenReturn(user);

    sut = new UserService(userRepository, sessionService);
  }
  
  @Test
  @DisplayName("Testing checkifEmailExists")
  public void testGetChatMessages() {
    //Arrange
    ArrayList<User> emptyList = new ArrayList<>();
    when(userRepository.findByEmailIgnoreCase("test@gmail.com")).thenReturn(emptyList);
    //Act
    Boolean emailExists = sut.checkIfEmailExists("test@gmail.com");
    //Assert
    assertFalse(emailExists);
  }
}
