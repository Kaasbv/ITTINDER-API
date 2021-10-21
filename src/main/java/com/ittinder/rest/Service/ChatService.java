package com.ittinder.rest.Service;
import com.ittinder.rest.Repositories.*;
import com.ittinder.rest.Entities.*;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ChatService {
  private final ChatRepository chatRepository;
  private final MessageRepository messageRepository;
  private final UserRepository userRepository;

  ChatService(ChatRepository repository, MessageRepository messageRepository, UserRepository userRepository) {
    this.chatRepository = repository;
    this.messageRepository = messageRepository;
    this.userRepository = userRepository;
  }

  public List<Message> getChatMessages(Long id, String startDate, String endDate){
    return messageRepository.findByCreatedDateBetweenAndChatId(startDate, endDate, id);
  }

  public Message postMessage(Long id, String message){
    User currentUser = userRepository.getById((long)1);
    Chat chat = chatRepository.getById(id);
    Message newMessage = new Message(currentUser, chat, message);
    
    messageRepository.save(newMessage);

    return newMessage;
  }

  public void deleteChat(Long id){
    chatRepository.deleteById(id);
  }
}
