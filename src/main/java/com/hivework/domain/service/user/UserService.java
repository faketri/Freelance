package com.hivework.domain.service.user;

import com.hivework.domain.entity.image.Image;
import com.hivework.domain.entity.user.Users;
import com.hivework.domain.repository.UsersRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@Service
public class UserService {

    private final UsersRepository usersRepository;

    public UserService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public Users findById(Long id) {
        return usersRepository.findById(id).orElse(null);
    }

    public List<Users> findAll() {
        return usersRepository.findAll();
    }

    public Users findByLogin(String login) {
        return usersRepository.findByLogin(login).orElse(null);
    }

    public Users getCurrentUser() {
        // Получение имени пользователя из контекста Spring Security
        var username = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();
        return findByLogin(username);
    }

    public Users updateImage(MultipartFile file) {
        try {
            final Users users = getCurrentUser();
            final String path = "/app/images/";

            if (file != null) {
                String imageName = path + users.getLogin().replace(' ', '-') + "-" + file.getOriginalFilename();
                System.out.println(imageName);
                try {
                    file.transferTo(Paths.get(imageName));
                } catch (IOException e) {
                    System.out.println(this.getClass() + " " + e.getMessage());
                }
                users.setProfileImage(new Image(null, imageName));
            }
            return usersRepository.save(users);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public Users save(Users users) {
        return usersRepository.save(users);
    }

    public void deleteById(Long id) {
        usersRepository.deleteById(id);
    }
}
