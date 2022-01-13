package com.ittinder.rest.Service;
import com.ittinder.rest.Repositories.*;
import com.ittinder.rest.Entities.*;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class ChatService {
  private final ChatRepository chatRepository;
  private final MessageRepository messageRepository;
  private final UserRepository userRepository;
  private final SessionService sessionService;

  public ChatService(
    ChatRepository repository,
    MessageRepository messageRepository,
    UserRepository userRepository,
    SessionService sessionService
    ) {
    this.chatRepository = repository;
    this.messageRepository = messageRepository;
    this.userRepository = userRepository;
    this.sessionService = sessionService;
  }

  // Retrieves all chat messages between a given time period based on given chat id
  public List<Message> getChatMessages(Long id, String startDate, String endDate){
    return messageRepository.findByCreatedDateBetweenAndChatId(startDate, endDate, id);
  }

  public Message postMessage(Long id, String message, HttpServletRequest request){
    User currentUser = sessionService.getUser(request);
    Chat chat = chatRepository.getById(id);
    Message newMessage = new Message(currentUser, chat, message);

    messageRepository.save(newMessage);

    return newMessage;
  }

  public void deleteChat(Long id){
    chatRepository.deleteById(id);
  }

  public List<Message> getMessagesByUserAndTime(String startDate, String endDate, HttpServletRequest request){
    User currentUser = sessionService.getUser(request);
    return messageRepository.findByCreatedDateBetweenAndUserId(startDate, endDate, currentUser.getId());
  }

  public List<Chat> getChatsFromUser(HttpServletRequest request) {
    User currentUser = sessionService.getUser(request);
    List<Chat> chats = chatRepository.findChatByAffectedUserIdOrInitiatedUserId(currentUser.getId(), currentUser.getId());

    for(Chat chat : chats){
      List<Message> lastMessages = messageRepository.findByChatIdOrderByCreatedDateDesc(chat.getId(), PageRequest.of(0,1));
      if(!lastMessages.isEmpty()){
        Message message = lastMessages.get(0);
        message.setChat(null);
        chat.setLastMessage(lastMessages.get(0));
      }
    }

    return chats;
  }
}
