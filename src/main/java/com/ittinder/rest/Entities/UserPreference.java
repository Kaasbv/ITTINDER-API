package com.ittinder.rest.Entities;

import java.beans.Transient;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UserPreference {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @OneToOne
  private User user;

  private String type;

  private String value;

  private static Map<String, String[]> preferences = Map.of(
    "musicgenres", new String[] {"pop", "rock", "hiphop", "jazz", "classical", "blues", "reggae", "country", "disco", "electronic", "folk", "latin", "metal", "rap", "soul", "techno", "other"},
    "programminglanguages", new String[] {"java", "c", "c++", "c#", "python", "javascript", "php", "ruby", "perl", "go", "scala", "swift", "objective-c", "other"}
  );

  public UserPreference() {
  }

  public UserPreference(User user, String type, String value){
    this.user = user;
    this.type = type;
    this.value = value;
  }

  public static Map<String, String[]> getPreferences() {
    return preferences;
  }

}