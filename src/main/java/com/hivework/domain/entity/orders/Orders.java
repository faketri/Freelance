package com.hivework.domain.entity.orders;

import com.hivework.domain.entity.payments.Payments;
import com.hivework.domain.entity.projects.Projects;
import com.hivework.domain.entity.user.Users;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @OneToOne
    @JoinColumn(name = "projects_id")
    private Projects projects;
    @ManyToOne
    @JoinColumn(name = "developer_id")
    private Users developer;
    @OneToOne
    @JoinColumn(name = "payments_id")
    private Payments payments;
    private Boolean isActive = true;
    private LocalDateTime dateOfCreate;

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

    public Projects getProjects() {
        return projects;
    }

    public void setProjects(Projects projects) {
        this.projects = projects;
    }

    public Users getDeveloper() {
        return developer;
    }

    public void setDeveloper(Users developer) {
        this.developer = developer;
    }

    public Payments getPayments() {
        return payments;
    }

    public void setPayments(Payments payments) {
        this.payments = payments;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
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

        Orders orders = (Orders) o;

        if (!id.equals(orders.id)) return false;
        if (!projects.equals(orders.projects)) return false;
        if (!developer.equals(orders.developer)) return false;
        if (!payments.equals(orders.payments)) return false;
        return dateOfCreate.equals(orders.dateOfCreate);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + projects.hashCode();
        result = 31 * result + developer.hashCode();
        result = 31 * result + payments.hashCode();
        result = 31 * result + dateOfCreate.hashCode();
        return result;
    }
}
