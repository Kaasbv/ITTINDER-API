package com.ittinder.rest.Repositories;

import com.ittinder.rest.Entities.Message;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
  List<Message> findByCreatedDateBetweenAndChatId(String from, String to, Long chatId);
  @Query("SELECT m FROM Message m WHERE (m.chat.affectedUser.id = ?3 or m.chat.initiatedUser.id = ?3) and m.createdDate >= ?1 and m.createdDate <= ?2")
  List<Message> findByCreatedDateBetweenAndUserId(String from, String to, Long userId);

  List<Message> findByChatIdOrderByCreatedDateDesc(Long chatId, Pageable pageable);
}