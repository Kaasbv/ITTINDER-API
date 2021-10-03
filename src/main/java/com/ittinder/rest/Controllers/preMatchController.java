package com.ittinder.rest.Controllers;

import com.ittinder.rest.Entities.preMatch;
import com.ittinder.rest.Repositories.preMatchRespository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.bind.annotation.RestController;

@RestController
public class preMatchController {
  //Add create cat method here
  private final com.ittinder.rest.Repositories.preMatchRespository preMatchRespository;

  preMatchController(preMatchRespository repository){
    this.preMatchRespository = repository;
  }

  @PostMapping("/preMatch")
  public String createPreMatch(@RequestBody String initiatedUser) {
    preMatch newPreMatch = new preMatch(initiatedUser);
    preMatchRespository.save(newPreMatch);
    return "Created preMatch: " + initiatedUser;
  }
}
