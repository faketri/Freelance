package com.hivework.domain.dto.response;

public class RatingResponseDto {

    private Long id;
    private String title;
    private UserResponse userTo;
    private UserResponse userFrom;
    private int grade;
    private String description;

    public RatingResponseDto() {
    }

    public RatingResponseDto(Long id, String title, UserResponse userTo, UserResponse userFrom, int grade, String description) {
        this.id = id;
        this.title = title;
        this.userTo = userTo;
        this.userFrom = userFrom;
        this.grade = grade;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public UserResponse getUserTo() {
        return userTo;
    }

    public void setUserTo(UserResponse userTo) {
        this.userTo = userTo;
    }

    public UserResponse getUserFrom() {
        return userFrom;
    }

    public void setUserFrom(UserResponse userFrom) {
        this.userFrom = userFrom;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
