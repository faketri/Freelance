package com.hivework.domain.repository;

import com.hivework.domain.entity.payments.Payments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentsRepository extends JpaRepository<Payments, Long> {
}