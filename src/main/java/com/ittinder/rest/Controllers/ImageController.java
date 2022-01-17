package com.ittinder.rest.Controllers;

import com.ittinder.rest.Entities.Image;
import com.ittinder.rest.Entities.User;
import com.ittinder.rest.Repositories.ImageRepository;
import com.ittinder.rest.Repositories.UserRepository;
import com.ittinder.rest.Service.ImageService;
import com.ittinder.rest.Service.SessionService;
import com.ittinder.rest.Util.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;


import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
public class ImageController {

  @Autowired
  private ImageRepository imageRepository;
  private final UserRepository userRepository;
  private final SessionService sessionService;

  ImageController(UserRepository repository, SessionService sessionService) {
    this.userRepository = repository;
    this.sessionService = sessionService;
  }

  @PostMapping("/user/image")
  public ResponseEntity<User> saveImage(@RequestParam MultipartFile multipartFile, HttpServletRequest request) throws IOException {
    User user = sessionService.getUser(request);

    String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
    String root = "public/";
    String uploadDir = "profile_images/" + RandomString.generate();
    user.setImage("/" + uploadDir + "/" + fileName);

    ImageService.saveFile(root + uploadDir, fileName, multipartFile);

    userRepository.save(user);

    return ResponseEntity.ok(sessionService.getUser(request));
  }
}
