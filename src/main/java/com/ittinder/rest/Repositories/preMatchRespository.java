package com.ittinder.rest.Repositories;

import com.ittinder.rest.Entities.User;
import com.ittinder.rest.Entities.preMatch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface preMatchRespository extends JpaRepository<preMatch, Long> {
    List<preMatch> findPreMatchByInitiatedUser_Id(Integer initiatedUser);
    preMatch getPreMatchByAffectedUserAndInitiatedUserOrInitiatedUserAndAffectedUser(User affectedUser, User initiatedUser, User initiatedUser2, User affectedUser1);
}