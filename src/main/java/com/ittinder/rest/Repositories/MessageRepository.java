package com.ittinder.rest.Repositories;

import com.ittinder.rest.Entities.Message;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MessageRepository extends JpaRepository<Message, Long> {
  // @Query("select * from message where timestamp > ?1 AND timestamp < ?2")
  // Object[] findByTimeCustomQuery(String dFrom, String dTo);
}