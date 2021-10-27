package com.ittinder.rest.Controllers;

import com.ittinder.rest.Entities.Chat;
import com.ittinder.rest.Entities.Message;
import com.ittinder.rest.Entities.User;
import com.ittinder.rest.Entities.preMatch;
import com.ittinder.rest.Service.ChatService;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ChatController {}
  private final ChatService chatService;

  ChatController(ChatService chatService) {
    this.chatService = chatService;
  }

  @GetMapping("/chat/{id}/messages")
  public List<Message> getChatMessages(@PathVariable Long id, @RequestParam String startDate, @RequestParam String endDate){
    return messageRepository.findByCreatedDateBetweenAndChatId(startDate, endDate, id);
  }

  @PostMapping("/chat/{id}/messages")
  public Message postMessage(@PathVariable Long id, @RequestBody String message){
    User currentUser = sessionService.getUser();
    Chat chat = chatRepository.getById(id);
    Message newMessage = new Message(currentUser, chat, message);
    
    messageRepository.save(newMessage);

    return newMessage;
  }

  @DeleteMapping("/chat/{id}")
  public ResponseEntity<HttpStatus> deleteChat(@PathVariable Long id){
    chatRepository.deleteById(id);
    return ResponseEntity.noContent().build();
  }

}
