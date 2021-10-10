package com.ittinder.rest.Repositories;

import com.ittinder.rest.Entities.preMatch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface preMatchRespository extends JpaRepository<preMatch, Long> {
    List<preMatch> findPreMatchByInitiatedUserContaining(String initiatedUser);
}