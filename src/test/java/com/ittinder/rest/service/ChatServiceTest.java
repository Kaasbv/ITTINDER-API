package com.ittinder.rest.service;
import org.junit.jupiter.api.BeforeEach;
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
  private ChatService chatService;

  @BeforeEach
  public void beforeEach() {
      ChatRepository chatRepository = mock(ChatRepository.class);
      MessageRepository messageRepository = mock(MessageRepository.class);
      UserRepository userRepository = mock(UserRepository.class);

      List<Message> messages = new ArrayList<Message>();
      messages.add(mock(Message.class));
      when(messageRepository.findByCreatedDateBetweenAndChatId("2021-01-01 00:00:00", "2021-01-01 00:00:00", (long)1)).thenReturn(messages);

      chatService = new ChatService(chatRepository, messageRepository, userRepository);
  } 

  @Test
  //Test getChatMessages
  public void testGetChatMessages() {
      List<Message> messages = chatService.getChatMessages((long)1, "2021-01-01 00:00:00", "2021-01-01 00:00:00");
      assertNotNull(messages);
  }
}
