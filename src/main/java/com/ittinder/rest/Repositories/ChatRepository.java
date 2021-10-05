package com.ittinder.rest.Repositories;

import com.ittinder.rest.Entities.Chat;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, Long> {

}