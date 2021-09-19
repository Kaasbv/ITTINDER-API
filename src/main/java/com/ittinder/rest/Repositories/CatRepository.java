package com.ittinder.rest.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ittinder.rest.Entities.Cat;

public interface CatRepository extends JpaRepository<Cat, Long> {

}