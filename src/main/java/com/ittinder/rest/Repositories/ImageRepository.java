package com.ittinder.rest.Repositories;

import com.ittinder.rest.Entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
