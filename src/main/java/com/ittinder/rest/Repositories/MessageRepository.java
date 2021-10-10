package com.ittinder.rest.Repositories;

import com.ittinder.rest.Entities.Message;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
  List<Message> findByCreatedDateBetweenAndChatId(String from, String to, Long chatId);
}