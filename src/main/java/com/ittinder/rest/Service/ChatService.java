package com.ittinder.rest.Service;
import com.ittinder.rest.Repositories.*;
import com.ittinder.rest.Entities.*;
import com.ittinder.rest.Service.SessionService;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ChatService {
  private final ChatRepository chatRepository;
  private final MessageRepository messageRepository;
  private final UserRepository userRepository;
  private final SessionService sessionService;

  public ChatService(
    ChatRepository repository,
    MessageRepository messageRepository,
    UserRepository userRepository
    SessionService sessionService
    ) {
    this.chatRepository = repository;
    this.messageRepository = messageRepository;
    this.userRepository = userRepository;
    this.sessionService = sessionService;
  }

  public List<Chat> getChatsFromUser() {
    User currentUser = sessionService.getUser();
    return chatRepository.findChatByAffectedUserId(initiatedUser)
  }

  public List<Message> getChatMessages(Long id, String startDate, String endDate){
    return messageRepository.findByCreatedDateBetweenAndChatId(startDate, endDate, id);
  }

  public Message postMessage(Long id, String message){
    User currentUser = sessionService.getUser();
    Chat chat = chatRepository.getById(id);
    Message newMessage = new Message(currentUser, chat, message);
    
    messageRepository.save(newMessage);

    return newMessage;
  }

  public void deleteChat(Long id){
    chatRepository.deleteById(id);
  }
}
