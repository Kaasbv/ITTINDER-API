package com.ittinder.rest.Controllers;

import com.ittinder.rest.Entities.Cat;
import com.ittinder.rest.Repositories.CatRepository;
// import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RestController;

@RestController
public class CatController {
  //Add create cat method here
  private final CatRepository catRepository;

  CatController(CatRepository repository){
    this.catRepository = repository;
  }

  @PostMapping("/cat")
  public String createCat(@RequestBody String name) {
    Cat newCat = new Cat(name);
    catRepository.save(newCat);
    return "Created cat: " + name;
  }
}
