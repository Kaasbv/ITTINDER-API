package com.ittinder.rest.Repositories;

import com.ittinder.rest.Entities.Chat;

import com.ittinder.rest.Entities.preMatch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Long> {
    List<Chat> findChatByAffectedUserIdOrInitiatedUserId(long affectedUserId, long initiatedUserId);

}