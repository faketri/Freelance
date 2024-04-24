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
    @Column(nullable = false)
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
    @ElementCollection(targetClass = ERole.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role",
            joinColumns = @JoinColumn(name = "users_id"))
    private final Set<ERole> roles = new HashSet<>();
    @ManyToMany
    private Set<Skills> skills = new HashSet<>();
    private Long balance = 0L;
    private LocalDateTime dateOfCreate;

    public Image getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(Image profileImage) {
        this.profileImage = profileImage;
    }

    public Users() {
    }

    public Users(Long id, String login, String email, String firstName, String lastName, String password,
                 Image profileImage, Set<Skills> skills, Long balance, LocalDateTime dateOfCreate) {
        this.id = id;
        this.login = login;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.profileImage = profileImage;
        this.skills = skills;
        this.balance = balance;
        this.dateOfCreate = dateOfCreate;
    }

    @PrePersist
    public void onCreate(){
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

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public LocalDateTime getDateOfCreate() {
        return dateOfCreate;
    }

    public void setDateOfCreate(LocalDateTime dateOfCreate) {
        this.dateOfCreate = dateOfCreate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Users users = (Users) o;

        if (!id.equals(users.id)) return false;
        if (!login.equals(users.login)) return false;
        if (!email.equals(users.email)) return false;
        if (!password.equals(users.password)) return false;
        if (!balance.equals(users.balance)) return false;
        return dateOfCreate.equals(users.dateOfCreate);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + login.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + balance.hashCode();
        result = 31 * result + dateOfCreate.hashCode();
        return result;
    }
}
