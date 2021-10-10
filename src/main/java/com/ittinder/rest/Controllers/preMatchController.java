package com.ittinder.rest.Controllers;

import com.ittinder.rest.Entities.preMatch;
import com.ittinder.rest.Repositories.preMatchRespository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class preMatchController {
  //Add create cat method here
  private final preMatchRespository preMatchRespository;

  preMatchController(preMatchRespository repository){
    this.preMatchRespository = repository;
  }


  @GetMapping("/getPreMatches")
  public List<preMatch> getAll(@RequestParam(required = false) String initiatedUser){
    List<preMatch> foundStudents = new ArrayList<>();

    if (initiatedUser == null || initiatedUser.isEmpty()) {
      foundStudents.addAll(preMatchRespository.findAll());
    }
    else {
      foundStudents.addAll(preMatchRespository.findPreMatchByInitiatedUserContaining(initiatedUser));
    }
    return foundStudents;
  }

/*
  public List<Question> getRandomQuestions(List<Questions> questions, int numberOfQuestions) {
    List<Question> randomQuestions = new ArrayList<>();
    List<Question> copy = new ArrayList<>(questions);

    SecureRandom rand = new SecureRandom();
    for (int i = 0; i < Math.min(numberOfQuestions, questions.size()); i++) {
      randomQuestions.add( copy.remove( rand.nextInt( copy.size() ) );
    }

    return randomQuestions;
  }
 */

  /*
  @GetMapping("userStream")
  public List<preMatch> getRandomUsers(int numberOfUsers){
  }

   */



  /*
  @PostMapping("/preMatch")
  public String createPreMatch(@RequestBody String initiatedUser) {
    preMatch newPreMatch = new preMatch(initiatedUser);
    preMatchRespository.save(newPreMatch);
    return "Created preMatch: " + initiatedUser;
  }
   */
}
