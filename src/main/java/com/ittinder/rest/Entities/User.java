package com.ittinder.rest.Entities;

import com.ittinder.rest.ValidPassword;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Getter
@Setter
@Entity
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotEmpty(message = "First name can't be empty")
  @Size(min = 2, max = 15)
  private String firstName;

  private String middleName;

  @NotEmpty(message = "Last name can't be empty")
  @Size(min = 2, max = 15)
  @NotEmpty(message = "Last name can't be empty")
  private String surname;

  @Past(message = "Date of birth must be in the past")
  @NotNull(message = "Date of birth must be filled in")
  private LocalDate dateOfBirth;

  @NotEmpty(message = "Email can't be empty")
  @Email(message = "Email should be valid")
  @Column(unique = true)
  private String email;

  @ValidPassword(message = "Password must contain a special and uppercase character, and be at least eight characters long")
  @NotEmpty(message = "Email can't be empty")
  private String password;
  private String matchingPassword;

  @NotEmpty(message = "You have to fill in a gender")
  private String gender;

  @NotEmpty(message = "You have to be interested in a gender")
  private String interestedInGender;

  @NotEmpty(message = "You have to fill in a description")
  @Size(min = 10, max = 256, message = "Description must be between 10 and 256 characters")
  private String description;

  private LocalDateTime lastLogin;
  private double latitude;
  private double longitude;
  private String currentLocation;

  // Constructor
  public User(String firstName,
              String middleName,
              String surname,
              LocalDate dateOfBirth,
              String email,
              String password,
              String gender,
              String interestedInGender,
              String description) {
    this.firstName = firstName;
    this.middleName = middleName;
    this.surname = surname;
    this.dateOfBirth = dateOfBirth;
    this.email = email;
    this.password = password;
    this.matchingPassword = matchingPassword;
    this.gender = gender;
    this.interestedInGender = interestedInGender;
    this.description = description;
  }

  public User() {
  }


  @Override
  public String toString() {
    return "User{" +
            "id=" + id +
            ", firstName='" + firstName + '\'' +
            ", middleName='" + middleName + '\'' +
            ", surname='" + surname + '\'' +
            ", dateOfBirth=" + dateOfBirth +
            ", email='" + email + '\'' +
            ", password='" + password + '\'' +
            ", gender='" + gender + '\'' +
            ", interestedInGender='" + interestedInGender + '\'' +
            ", description='" + description + '\'' +
            ", lastLogin=" + lastLogin +
            ", latitude=" + latitude +
            ", longitude=" + longitude +
            ", currentLocation='" + currentLocation + '\'' +
            '}';
  }
}
