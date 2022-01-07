package com.ittinder.rest.Repositories;

import com.ittinder.rest.Entities.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session, Long> {
  Session getBySessionId(String sessionId);
}
