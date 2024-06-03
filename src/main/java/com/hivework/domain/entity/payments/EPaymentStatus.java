package com.hivework.domain.entity.payments;

public enum EPaymentStatus {
    AWAIT_PAYMENT("Ожидает оплаты."),
    PAID("Оплачен.");

    private String name;

    EPaymentStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
