package com.hivework.domain.mapper;

import com.hivework.domain.dto.response.PaymentsResponseDto;
import com.hivework.domain.entity.payments.EPaymentStatus;
import com.hivework.domain.entity.payments.Payments;

public class PaymentsMapper {

    public static PaymentsResponseDto toDto(Payments payments) {
        if (payments == null) {
            return null;
        }

        PaymentsResponseDto dto = new PaymentsResponseDto();
        dto.setId(payments.getId());
        dto.setUserPayment(UsersMapper.toResponse(payments.getUserPayment()));
        dto.setUsersTakes(UsersMapper.toResponse(payments.getUsersTakes()));
        dto.setPrice(payments.getPrice());
        dto.setePaymentStatus(payments.getePaymentStatus().getName());
        dto.setDateOfCreate(payments.getDateOfCreate());

        return dto;
    }

    public static Payments toEntity(PaymentsResponseDto dto) {
        if (dto == null) {
            return null;
        }

        Payments payments = new Payments();
        payments.setId(dto.getId());
        payments.setUserPayment(UsersMapper.toObj(dto.getUserPayment()));
        payments.setUsersTakes(UsersMapper.toObj((dto.getUsersTakes())));
        payments.setPrice(dto.getPrice());
        payments.setePaymentStatus(EPaymentStatus.valueOf(dto.getePaymentStatus()));
        payments.setDateOfCreate(dto.getDateOfCreate());

        return payments;
    }
}
