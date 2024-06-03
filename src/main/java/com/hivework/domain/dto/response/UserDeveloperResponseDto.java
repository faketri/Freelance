package com.hivework.domain.dto.response;

public class UserDeveloperResponseDto {
    private Long id;
    private UserResponse usersDeveloper;
    private String message;

    public UserDeveloperResponseDto() {

    }

    public UserDeveloperResponseDto(Long id, UserResponse usersDeveloper, String message) {
        this.id = id;
        this.usersDeveloper = usersDeveloper;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserResponse getUsersDeveloper() {
        return usersDeveloper;
    }

    public void setUsersDeveloper(UserResponse usersDeveloper) {
        this.usersDeveloper = usersDeveloper;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
