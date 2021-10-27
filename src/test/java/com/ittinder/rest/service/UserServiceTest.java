package com.ittinder.rest.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
    when(userRepository.findByEmailIgnoreCase("test@gmail.com").isEmpty()).thenReturn(true);
    //Act
    Boolean emailExists = sut.checkIfEmailExists("test@gmail.com");
    //Assert
    assertTrue(emailExists);
  }
}
