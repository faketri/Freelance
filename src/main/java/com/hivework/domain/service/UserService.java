package com.hivework.domain.service;

import com.hivework.domain.entity.user.Users;
import com.hivework.domain.repository.UsersRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UsersRepository usersRepository;

    public UserService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public Users findById(Long id){
        return usersRepository.findById(id).orElse(null);
    }

    public Users findByLogin(String login) {
        return usersRepository.findByLogin(login).orElse(null);
    }

    public Users save(Users users){
        return usersRepository.save(users);
    }
}
