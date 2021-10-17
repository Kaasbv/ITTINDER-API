package com.ittinder.rest.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
  private String surname;

  @Past
  private LocalDate dateOfBirth;

  @NotEmpty
  @Email(message = "Email should be valid")
  private String email;

  //Create custom annotation @ValidPassword
  private String password;

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

  // constructor
  // Nog niet zeker welke velden mee worden genomen in defitinieve constructor
  public User(String firstName, String middleName, String surname, String email) {
    this.firstName = firstName;
    this.middleName = middleName;
    this.surname = surname;
    this.email = email;
  }

  // overloading constructor
  public User(String firstName) {
    this.firstName = firstName;
  }

  public User(){}


  /****************GETTERS****************/
  public Long getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getMiddleName() {
    return middleName;
  }

  public String getSurname() {
    return surname;
  }

  public LocalDate getDateOfBirth() {
    return dateOfBirth;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public String getCurrentLocation() {
    return currentLocation;
  }

  public String getGender() {
    return gender;
  }

  public LocalDateTime getLastLogin() {
    return lastLogin;
  }

  public double getLatitude() {
    return latitude;
  }

  public double getLongitude() {
    return longitude;
  }

  public String getInterestedInGender() {
    return interestedInGender;
  }

  public String getDescription() {
    return description;
  }
  /****************END OF GETTERS****************/

  /****************SETTERS****************/
  public void setId(Long id) {
    this.id = id;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public void setDateOfBirth(LocalDate dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setCurrentLocation(String currentLocation) {
    this.currentLocation = currentLocation;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public void setLastLogin(LocalDateTime lastLogin) {
    this.lastLogin = lastLogin;
  }

  public void setLatitude(double latitude) {
    this.latitude = latitude;
  }

  public void setLongitude(double longitude) {
    this.longitude = longitude;
  }

  public void setInterestedInGender(String interestedInGender) {
    this.interestedInGender = interestedInGender;
  }

  public void setDescription(String description) {
    this.description = description;
  }
  /****************END OF SETTERS****************/
}
