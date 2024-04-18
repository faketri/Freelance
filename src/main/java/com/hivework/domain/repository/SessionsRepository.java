package com.hivework.domain.repository;


import com.hivework.domain.entity.session.Sessions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface SessionsRepository extends JpaRepository<Sessions, Long> {

    Optional<Sessions> findBySessionId(String sessionId);

    Optional<Sessions> findByUsername(String username);
}