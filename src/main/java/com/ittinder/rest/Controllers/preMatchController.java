package com.ittinder.rest.Controllers;

import com.ittinder.rest.Entities.User;
import com.ittinder.rest.Entities.preMatch;
import com.ittinder.rest.Entities.Chat;
import com.ittinder.rest.Repositories.UserRepository;
import com.ittinder.rest.Repositories.preMatchRespository;
import com.ittinder.rest.Repositories.ChatRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class preMatchController {
  private final com.ittinder.rest.Repositories.preMatchRespository preMatchRespository;
  private final UserRepository userRepository;
  private final ChatRepository chatRepository;

  preMatchController(preMatchRespository repository, UserRepository userRepository, ChatRepository chatRepository){
    this.preMatchRespository = repository;
    this.userRepository = userRepository;
    this.chatRepository = chatRepository;
  }
  @GetMapping("/getPreMatches")
  public List<preMatch> getAll(@RequestParam(required = false) Integer initiatedUser){
    List<preMatch> foundMatches = new ArrayList<>();

    if (initiatedUser == null) {
      foundMatches.addAll(preMatchRespository.findAll());
    }
    else {
      foundMatches.addAll(preMatchRespository.findPreMatchByInitiatedUser_Id(initiatedUser));
    }
    return foundMatches;
  }



  @PostMapping("/SwipeRight")
  @ResponseBody
  public ResponseEntity<HttpStatus> SwipeRight(@RequestParam long idUser1, long idUser2){
    User user1 = userRepository.getUserById(idUser1);
    User user2 = userRepository.getUserById(idUser2);
    preMatch preMatchFound = preMatchRespository.getPreMatchByAffectedUserAndInitiatedUserOrInitiatedUserAndAffectedUser(user1, user2, user1, user2);
    Date date = new Date();

    //checks which user is registred as which user in the table
    if (!(preMatchFound == null)){
      if (preMatchFound.getInitiatedUser().getId() == idUser1){
        preMatchFound.setInitiatedUserChoice(true);
        preMatchFound.setInitiatedUserChosen(true);
      }
      else{
        preMatchFound.setAffectedUserChoice(true);
        preMatchFound.setAffectedUserChosen(true);
      }
      //If both values are true a new chat is created with both users assigned to the new chat
      if (preMatchFound.isAffectedUserChoice() == true && preMatchFound.isInitiatedUserChoice() == true) {
        createChat(preMatchFound);
      }
      preMatchFound.setChangedDate(date);
      preMatchRespository.save(preMatchFound);
    }
    else {
      //create new prematch
      preMatch preMatch = new preMatch();
      preMatch.setInitiatedUser(user1);
      preMatch.setAffectedUser(user2);
      preMatch.setInitiatedUserChoice(true);
      preMatch.setInitiatedUserChosen(true);
      preMatch.setChangedDate(date);

      //save changes
      preMatchRespository.save(preMatch);
    }
    return ResponseEntity.ok(HttpStatus.OK);
  }

  @PostMapping("/SwipeLeft")
  @ResponseBody
  public ResponseEntity<HttpStatus> SwipeLeft(@RequestParam long idUser1, long idUser2){
    User user1 = userRepository.getUserById(idUser1);
    User user2 = userRepository.getUserById(idUser2);
    preMatch preMatchFound = preMatchRespository.getPreMatchByAffectedUserAndInitiatedUserOrInitiatedUserAndAffectedUser(user1, user2, user1, user2);
    Date date = new Date();

    //checks which user is registered as which user in the table
    if (!(preMatchFound == null)){
      if (preMatchFound.getInitiatedUser().getId() == idUser1){
        preMatchFound.setInitiatedUserChoice(false);
        preMatchFound.setInitiatedUserChosen(true);
      }
      else{
        preMatchFound.setAffectedUserChoice(false);
        preMatchFound.setAffectedUserChosen(true);
      }
      preMatchFound.setChangedDate(date);
      preMatchRespository.save(preMatchFound);
    }
    else {

      //create new prematch
      preMatch preMatch = new preMatch();
      preMatch.setInitiatedUser(user1);
      preMatch.setAffectedUser(user2);
      preMatch.setInitiatedUserChoice(false);
      preMatch.setInitiatedUserChosen(true);
      preMatch.setChangedDate(date);

      //save changes
      preMatchRespository.save(preMatch);
    }
    return ResponseEntity.ok(HttpStatus.OK);
  }

  public void createChat(preMatch preMatch) {
      Chat chat = new Chat();
      chat.setAffectedUser(preMatch.getAffectedUser());
      chat.setInitiatedUser(preMatch.getInitiatedUser());
      chatRepository.save(chat);
  }
}
