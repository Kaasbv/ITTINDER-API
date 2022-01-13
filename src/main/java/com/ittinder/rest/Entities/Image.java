package com.ittinder.rest.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ittinder.rest.Service.ImageService;
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

  @JsonBackReference(value="user-images")
  @ManyToOne
  private User user;

  private int sortNr;

  private String profileImagePath;

  private String image;

  public Image() {
  }

  public Image(User user) {
    this.user = user;
  }

  public String getPhotosImagePath() {
    if (image == null || imageId == null) return null;

    return "/profile_images/" + imageId + "/" + image;
  }
}
