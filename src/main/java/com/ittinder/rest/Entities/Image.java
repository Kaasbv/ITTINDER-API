package com.ittinder.rest.Entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ittinder.rest.Services.ImageService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Getter
@Setter
@Entity
public class Image {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long imageId;

  @ManyToOne
  private User user;

  private int sortNr;

  private String profileImagePath;

  private String image;

//  public Image(String user, String image) {
//    this.user = user;
//    this.image = image;
//  }

  public Image() {
  }

  public Image(User user) {
  }

  public Image(User user, MultipartFile multipartFile) {
    this.user = user;
  }
}
