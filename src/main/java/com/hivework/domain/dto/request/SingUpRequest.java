package com.hivework.domain.dto.request;

public class SingUpRequest {

    private String login;
    private String email;
    private String telegramUrl;
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
}
