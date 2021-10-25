package com.ittinder.rest.Controllers;

import com.ittinder.rest.Entities.User;
import com.ittinder.rest.Entities.preMatch;
import com.ittinder.rest.Repositories.UserRepository;
import com.ittinder.rest.Repositories.preMatchRespository;
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

  preMatchController(preMatchRespository repository, UserRepository userRepository){
    this.preMatchRespository = repository;
    this.userRepository = userRepository;
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
  public ResponseEntity<HttpStatus> SwipeRight(@RequestParam int idUser1, int idUser2){
    User user1 = userRepository.getUserById(idUser1);
    User user2 = userRepository.getUserById(idUser2);
    preMatch preMatchFound = preMatchRespository.getPreMatchByAffectedUserAndInitiatedUserOrInitiatedUserAndAffectedUser(user1, user2, user1, user2);
    Date date = new Date();

    if (!(preMatchFound == null)){
      if (preMatchFound.getInitiatedUser().getId() == idUser1){
        preMatchFound.setInitiatedUserChoice(true);
      }
      else{
        preMatchFound.setAffectedUserChoice(true);
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
      preMatch.setChangedDate(date);

      //save changes
      preMatchRespository.save(preMatch);
    }
    return ResponseEntity.ok(HttpStatus.OK);
  }

  @PostMapping("/SwipeLeft")
  @ResponseBody
  public ResponseEntity<HttpStatus> SwipeLeft(@RequestParam Integer idUser1, Integer idUser2){
    User user1 = userRepository.getUserById(idUser1);
    User user2 = userRepository.getUserById(idUser2);
    preMatch preMatchFound = preMatchRespository.getPreMatchByAffectedUserAndInitiatedUserOrInitiatedUserAndAffectedUser(user1, user2, user1, user2);
    Date date = new Date();

    if (!(preMatchFound == null)){
      if (preMatchFound.getInitiatedUser().getId() == idUser1){
        preMatchFound.setInitiatedUserChoice(false);
      }
      else{
        preMatchFound.setAffectedUserChoice(false);
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
      preMatch.setChangedDate(date);

      //save changes
      preMatchRespository.save(preMatch);
    }
    return ResponseEntity.ok(HttpStatus.OK);
  }

}
