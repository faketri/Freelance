package com.hivework.domain.dto.response;

import com.hivework.domain.entity.payments.EPaymentStatus;

import java.time.LocalDateTime;

public class PaymentsResponseDto {
    private Long id;
    private UserResponse userPayment;
    private UserResponse usersTakes;
    private Long price;
    private String ePaymentStatus;
    private LocalDateTime dateOfCreate;

    public PaymentsResponseDto() {
    }

    public PaymentsResponseDto(Long id, UserResponse userPayment, UserResponse usersTakes, Long price, EPaymentStatus ePaymentStatus, LocalDateTime dateOfCreate) {
        this.id = id;
        this.userPayment = userPayment;
        this.usersTakes = usersTakes;
        this.price = price;
        this.ePaymentStatus = ePaymentStatus.getName();
        this.dateOfCreate = dateOfCreate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserResponse getUserPayment() {
        return userPayment;
    }

    public void setUserPayment(UserResponse userPayment) {
        this.userPayment = userPayment;
    }

    public UserResponse getUsersTakes() {
        return usersTakes;
    }

    public void setUsersTakes(UserResponse usersTakes) {
        this.usersTakes = usersTakes;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getePaymentStatus() {
        return ePaymentStatus;
    }

    public void setePaymentStatus(String ePaymentStatus) {
        this.ePaymentStatus = ePaymentStatus;
    }

    public LocalDateTime getDateOfCreate() {
        return dateOfCreate;
    }

    public void setDateOfCreate(LocalDateTime dateOfCreate) {
        this.dateOfCreate = dateOfCreate;
    }
}
