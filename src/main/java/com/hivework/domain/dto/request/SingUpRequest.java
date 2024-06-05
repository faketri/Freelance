package com.hivework.domain.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

public class SingUpRequest {
    @Size(min = 5, max = 50,
            message = "Имя пользователя должно содержать от 5 до 50 символов")
    @NotBlank(message = "Имя пользователя не может быть пустыми")
    private String login;

    @NotBlank(message = "Адрес электронной почты не может быть пустыми")
    @Email(message = "Email адрес должен быть в формате user@example.com")
    private String email;

    @URL(message = "URL адрес должен быть в формате https://www.google.com/")
    private String telegramUrl;
    @Size(min = 8, message = "Пароль должен содержать мининум 8 символов")
    @NotBlank(message = "Пароль не может быть пустыми")
    private String password;

    public SingUpRequest() {
    }

    public SingUpRequest(String login, String email, String telegramUrl, String password) {
        this.login = login;
        this.email = email;
        this.telegramUrl = telegramUrl;
        this.password = password;
    }

    public String getTelegramUrl() {
        return telegramUrl;
    }

    public void setTelegramUrl(String telegramUrl) {
        this.telegramUrl = telegramUrl;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "SingUpRequest{" +
                "login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", telegramUrl='" + telegramUrl + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
