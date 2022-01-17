package com.ittinder.rest.Entities;
import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.ittinder.rest.ValidPassword;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Getter
@Setter
@Entity
@Table(name = "user")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String firstName;

  private String middleName;

  private String surname;

  private LocalDate dateOfBirth;

  private String email;

  private String password;

  //Gender options on frontend
  private String gender;

  //Gender options on frontend
  private String interestedInGender;

  private String description;

  private String image;

  private LocalDateTime lastLogin;
  private double latitude;
  private double longitude;
  private String currentLocation;

  @JsonIgnore
  @JsonManagedReference(value="user-prematch-initiated")
  @OneToMany(mappedBy = "initiatedUser", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  Set<preMatch> preMatchAsInitiated;

  @JsonIgnore
  @JsonManagedReference(value="user-prematch-affected")
  @OneToMany(mappedBy = "affectedUser", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  Set<preMatch> preMatchAsAffected;

  // Constructor
  public User(String firstName,
              String middleName,
              String surname,
              LocalDate dateOfBirth,
              String email,
              String password,
              String gender,
              String interestedInGender,
              String description,
              String image) {
    this.firstName = firstName;
    this.middleName = middleName;
    this.surname = surname;
    this.dateOfBirth = dateOfBirth;
    this.email = email;
    this.password = password;
    this.gender = gender;
    this.interestedInGender = interestedInGender;
    this.description = description;
    this.image = image;
  }

  //Empty constructor for JPA
  public User() {
  }
}
