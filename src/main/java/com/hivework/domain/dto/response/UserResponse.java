package com.hivework.domain.dto.response;

import com.hivework.domain.entity.image.Image;
import com.hivework.domain.entity.skills.Skills;
import com.hivework.domain.entity.user.ERole;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class UserResponse {

    private Long id;
    private String login;
    private String email;
    private String firstName;
    private String lastName;
    private Image profileImage;
    private String telegramUrl;
    private Set<Skills> skills = new HashSet<>();
    private Set<ERole> roles = new HashSet<>();
    private LocalDateTime dateOfCreate;

    public UserResponse() {
    }

    public UserResponse(Long id, String login, String email, String firstName, String lastName, Image profileImage, String telegramUrl, Set<Skills> skills, Set<ERole> roles, LocalDateTime dateOfCreate) {
        this.id = id;
        this.login = login;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.profileImage = profileImage;
        this.telegramUrl = telegramUrl;
        this.skills = skills;
        this.roles = roles;
        this.dateOfCreate = dateOfCreate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Image getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(Image profileImage) {
        this.profileImage = profileImage;
    }

    public Set<Skills> getSkills() {
        return skills;
    }

    public void setSkills(Set<Skills> skills) {
        this.skills = skills;
    }

    public LocalDateTime getDateOfCreate() {
        return dateOfCreate;
    }

    public void setDateOfCreate(LocalDateTime dateOfCreate) {
        this.dateOfCreate = dateOfCreate;
    }

    public Set<ERole> getRoles() {
        return roles;
    }

    public void setRoles(Set<ERole> roles) {
        this.roles = roles;
    }

    public String getTelegramUrl() {
        return telegramUrl;
    }

    public void setTelegramUrl(String telegramUrl) {
        this.telegramUrl = telegramUrl;
    }
}
