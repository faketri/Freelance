package com.hivework.domain.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class SingInRequest {

    @Size(min = 5, max = 50,
            message = "Имя пользователя должно содержать от 5 до 50 символов")
    @NotBlank(message = "Имя пользователя не может быть пустыми")
    private String login;
    @Size(min = 8, message = "Пароль должен содержать мининум 8 символов")
    @NotBlank(message = "Пароль не может быть пустыми")
    private String password;


    public SingInRequest() {
    }

    public SingInRequest(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
