package com.hivework.domain.entity.user;

import com.hivework.domain.entity.image.Image;
import com.hivework.domain.entity.skills.Skills;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String telegramUrl;
    @Column(nullable = false, unique = true)
    private String login;
    @Column(nullable = false)
    private String email;
    private String firstName;
    private String lastName;
    @Column(nullable = false)
    private String password;
    @ManyToOne
    @JoinColumn(name = "profile_image_id")
    private Image profileImage;
    @ManyToMany
    private Set<Skills> skills = new HashSet<>();
    @ElementCollection(targetClass = ERole.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role",
            joinColumns = @JoinColumn(name = "users_id"))
    private Set<ERole> roles = new HashSet<>();
    private LocalDateTime dateOfCreate;

    public Users() {
    }

    public Users(Long id, String telegramUrl, String login, String email, String firstName, String lastName, String password, Image profileImage, Set<Skills> skills, Set<ERole> roles, LocalDateTime dateOfCreate) {
        this.id = id;
        this.telegramUrl = telegramUrl;
        this.login = login;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.profileImage = profileImage;
        this.skills = skills;
        this.roles = roles;
        this.dateOfCreate = dateOfCreate;
    }

    public Image getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(Image profileImage) {
        this.profileImage = profileImage;
    }

    public String getTelegramUrl() {
        return telegramUrl;
    }

    public void setTelegramUrl(String telegramUrl) {
        this.telegramUrl = telegramUrl;
    }

    @PrePersist
    public void onCreate() {
        this.dateOfCreate = LocalDateTime.now();
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<ERole> getRoles() {
        return roles;
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
}
