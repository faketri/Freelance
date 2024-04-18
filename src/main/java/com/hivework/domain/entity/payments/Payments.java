package com.hivework.domain.entity.payments;

import com.hivework.domain.entity.user.Users;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Payments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @OneToOne
    @JoinColumn(name = "user_payment_id")
    private Users userPayment;
    @OneToOne
    @JoinColumn(name = "users_takes_id")
    private Users usersTakes;
    private Long price;
    private EPaymentStatus ePaymentStatus = EPaymentStatus.AWAIT_PAYMENT;
    private LocalDateTime dateOfCreate;

    public Payments() {
    }

    public Payments(Long id, Users userPayment, Users usersTakes, Long price, EPaymentStatus ePaymentStatus, LocalDateTime dateOfCreate) {
        this.id = id;
        this.userPayment = userPayment;
        this.usersTakes = usersTakes;
        this.price = price;
        this.ePaymentStatus = ePaymentStatus;
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

    public Users getUsersTakes() {
        return usersTakes;
    }

    public void setUsersTakes(Users usersTakes) {
        this.usersTakes = usersTakes;
    }

    public Users getUserPayment() {
        return userPayment;
    }

    public void setUserPayment(Users userPayment) {
        this.userPayment = userPayment;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public LocalDateTime getDateOfCreate() {
        return dateOfCreate;
    }

    public void setDateOfCreate(LocalDateTime dateOfCreate) {
        this.dateOfCreate = dateOfCreate;
    }

    public EPaymentStatus getePaymentStatus() {
        return ePaymentStatus;
    }

    public void setePaymentStatus(EPaymentStatus ePaymentStatus) {
        this.ePaymentStatus = ePaymentStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Payments payments = (Payments) o;

        if (!id.equals(payments.id)) return false;
        if (!userPayment.equals(payments.userPayment)) return false;
        if (!usersTakes.equals(payments.usersTakes)) return false;
        if (!price.equals(payments.price)) return false;
        if (ePaymentStatus != payments.ePaymentStatus) return false;
        return dateOfCreate.equals(payments.dateOfCreate);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + userPayment.hashCode();
        result = 31 * result + usersTakes.hashCode();
        result = 31 * result + price.hashCode();
        result = 31 * result + ePaymentStatus.hashCode();
        result = 31 * result + dateOfCreate.hashCode();
        return result;
    }
}
