package com.ittinder.rest.Controllers;

import com.ittinder.rest.Repositories.UserPreferenceRepository;
import com.ittinder.rest.Service.SessionService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ittinder.rest.Entities.User;
import com.ittinder.rest.Entities.UserPreference;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/preferences")
public class PreferencesController {
  private final UserPreferenceRepository userPreferenceRepository;
  private final SessionService sessionService;

  PreferencesController(UserPreferenceRepository repository, SessionService sessionService) {
    this.userPreferenceRepository = repository;
    this.sessionService = sessionService;
  }

  @GetMapping
  public ResponseEntity<Map<String, String[]>> getPreferences(){
    return ResponseEntity.ok(UserPreference.getPreferences());
  }

  @GetMapping("/user")
  public ResponseEntity<Map<String, Map<String, Boolean>>> getUserPreferences(){
    //First grab user preferences
    User currentUser = sessionService.getUser();
    List<UserPreference> userPreferences = userPreferenceRepository.findByUserId(currentUser.getId());
    Map<String, String[]> preferences = UserPreference.getPreferences();
    Map<String, Map<String, Boolean>> userPreferencesMap = new HashMap<>();

    preferences.forEach((String type, String options[]) -> {
      Map<String, Boolean> preferenceMap = new HashMap<>();
      for(String option : options){
        boolean isSelected = false;
        for(UserPreference userPreference : userPreferences){
          if(userPreference.getType().equals(type) && userPreference.getValue().equals(option)){
            isSelected = true;
            break;
          }
        }
        preferenceMap.put(option, isSelected);
      }
      userPreferencesMap.put(type, preferenceMap);
    });

    return ResponseEntity.ok(userPreferencesMap);
  }
}
