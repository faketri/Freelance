package com.hivework.domain.entity.rating;

import com.hivework.domain.entity.user.Users;
import jakarta.persistence.*;

@Entity
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String title;
    @ManyToOne
    @JoinColumn(name = "user_to_id")
    private Users userTo;
    @ManyToOne
    @JoinColumn(name = "user_from_id")
    private Users userFrom;
    private int grade;
    private String description;

    public Rating() {
    }

    public Rating(Long id, String title, Users userTo, Users userFrom, int grade, String description) {
        this.id = id;
        this.title = title;
        this.userTo = userTo;
        this.userFrom = userFrom;
        this.grade = grade;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Users getUserTo() {
        return userTo;
    }

    public void setUserTo(Users userTo) {
        this.userTo = userTo;
    }

    public Users getUserFrom() {
        return userFrom;
    }

    public void setUserFrom(Users userFrom) {
        this.userFrom = userFrom;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
