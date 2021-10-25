package com.ittinder.rest.Repositories;

import java.util.List;
import java.util.Optional;

import com.ittinder.rest.Entities.UserPreference;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPreferenceRepository extends JpaRepository<UserPreference, Long> {
  List<UserPreference> findByUserId(Long userId);
  Optional<UserPreference> findByValueAndTypeAndUserId(String value, String type, Long userId);
}