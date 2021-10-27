package com.ittinder.rest.Entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ittinder.rest.ValidPassword;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
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

  @Size(max = 6)
  private String middleName;

  @NotEmpty(message = "Last name can't be empty")
  @Size(min = 2, max = 15)
  private String surname;

  @Past(message = "Date of birth must be in the past")
  @NotNull(message = "Date of birth must be filled in")
  private LocalDate dateOfBirth;

  @Email(message = "Email should be valid")
  @Column(unique = true)
  @NotNull
  private String email;

  @ValidPassword(message = "Password must contain a special and uppercase character, and have a length of at least eight characters")
  @NotEmpty(message = "password can't be empty")
  private String password;

  //Gender options on frontend
  private String gender;

  //Gender options on frontend
  private String interestedInGender;

  @Size(min = 10, max = 256, message = "Description must be between 10 and 256 characters")
  private String description;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "image")
  private Set<Image> image;

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
              String description,
              Set<Image> image) {
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
