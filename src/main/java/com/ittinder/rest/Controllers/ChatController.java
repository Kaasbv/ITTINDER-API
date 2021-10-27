package com.ittinder.rest.Controllers;

import com.ittinder.rest.Entities.Message;
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

import java.util.List;

@RestController
public class ChatController {
  private final ChatService chatService;

  ChatController(ChatService chatService) {
    this.chatService = chatService;
  }

  @GetMapping("/chat/{id}/messages")
  public List<Message> getChatMessages(@PathVariable Long id, @RequestParam String startDate, @RequestParam String endDate){
    return chatService.getChatMessages(id, startDate, endDate);
  }

  @PostMapping("/chat/{id}/messages")
  public void postMessage(@PathVariable Long id, @RequestBody String message){
    chatService.postMessage(id, message);
  }

  @DeleteMapping("/chat/{id}")
  public ResponseEntity<HttpStatus> deleteChat(@PathVariable Long id){
    chatService.deleteChat(id);
    return ResponseEntity.noContent().build();
  }
}
