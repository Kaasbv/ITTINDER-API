package com.ittinder.rest.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.ittinder.rest.Repositories.ChatRepository;
import com.ittinder.rest.Repositories.MessageRepository;
import com.ittinder.rest.Repositories.UserRepository;
import com.ittinder.rest.Entities.Chat;
import com.ittinder.rest.Entities.Message;
import com.ittinder.rest.Entities.User;
import com.ittinder.rest.Service.ChatService;

public class ChatServiceTest {
  private ChatService sut;
  private ChatRepository chatRepository;
  private MessageRepository messageRepository;
  private UserRepository userRepository;

  @BeforeEach
  public void beforeEach() {
    chatRepository = mock(ChatRepository.class);
    messageRepository = mock(MessageRepository.class);
    userRepository = mock(UserRepository.class);

    User user = mock(User.class);
    when(userRepository.getById((long)1)).thenReturn(user);

    sut = new ChatService(chatRepository, messageRepository, userRepository);
  } 
  
  @Test
  @DisplayName("Testing getChatMessages")
  public void testGetChatMessages() {
    //Arrange
    List<Message> arrangedMessages = new ArrayList<Message>();
    arrangedMessages.add(mock(Message.class));
    when(messageRepository.findByCreatedDateBetweenAndChatId("2021-01-01 00:00:00", "2021-01-01 00:00:00", (long)1)).thenReturn(arrangedMessages);
    //Act
    List<Message> messages = sut.getChatMessages((long)1, "2021-01-01 00:00:00", "2021-01-01 00:00:00");
    //Assert
    assertNotNull(messages);
  }

  @Test
  @DisplayName("Testing postMessage")
  public void testPostMessage() {
    //Arrange
    Chat arrangedChat = mock(Chat.class);
    when(chatRepository.getById((long)1)).thenReturn(arrangedChat);
    //Act
    Message message = sut.postMessage((long)1, "Ik ben jimmy en ik houd heel erg van programmeren");
    //Assert
    assertNotNull(message);
  }
}
