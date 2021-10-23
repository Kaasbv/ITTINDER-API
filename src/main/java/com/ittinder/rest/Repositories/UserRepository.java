package com.ittinder.rest.Repositories;

import com.ittinder.rest.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

  List<User> findByEmailIgnoreCase(String email);

  User getUserById(Long id);

}
