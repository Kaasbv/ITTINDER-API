package com.ittinder.rest.Repositories;

import com.ittinder.rest.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
