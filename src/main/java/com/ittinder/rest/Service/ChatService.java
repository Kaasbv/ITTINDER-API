package com.ittinder.rest.Service;
import com.ittinder.rest.Repositories.*;
import com.ittinder.rest.Entities.*;
import java.util.List;

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

  public List<Chat> getChatsFromUser(HttpServletRequest request) {
    User currentUser = sessionService.getUser(request);
    List<Chat> chats = chatRepository.findChatByAffectedUserIdOrInitiatedUserId(currentUser.getId(), currentUser.getId());

    for(Chat chat : chats){
      Message lastMessage = messageRepository.getByChatIdOrderByCreatedDateDesc(chat.getId());
      chat.setLastMessage(lastMessage);
    }

    return chats;
  }
}
