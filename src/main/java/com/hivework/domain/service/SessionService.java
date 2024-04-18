package com.hivework.domain.service;

import com.hivework.domain.entity.session.Sessions;
import com.hivework.domain.repository.SessionsRepository;
import org.springframework.session.SessionRepository;
import org.springframework.stereotype.Service;

@Service
public class SessionService {

    private final SessionsRepository sessionRepository;

    public SessionService(SessionsRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public Sessions findById(Long id){
        return sessionRepository.findById(id).orElse(null);
    }

    public Sessions findBySessionsId(String id){
        return sessionRepository.findBySessionId(id).orElse(null);
    }
    public Sessions findByUsername(String username){
        return sessionRepository.findByUsername(username).orElse(null);
    }

    public Sessions save(Sessions sessions){
        return sessionRepository.save(sessions);
    }
}
