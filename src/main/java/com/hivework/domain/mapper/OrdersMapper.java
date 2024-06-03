package com.hivework.domain.mapper;

import com.hivework.domain.dto.response.OrdersResponseDto;
import com.hivework.domain.entity.orders.Orders;

public class OrdersMapper {

    public static OrdersResponseDto toDto(Orders orders) {
        if (orders == null) {
            return null;
        }

        OrdersResponseDto dto = new OrdersResponseDto();
        dto.setId(orders.getId());
        dto.setProjects(orders.getProjects());
        dto.setDeveloper(UsersMapper.toResponse(orders.getDeveloper()));
        dto.setPayments(PaymentsMapper.toDto(orders.getPayments()));
        dto.setActive(orders.getActive());
        dto.setDateOfCreate(orders.getDateOfCreate());

        return dto;
    }

    public static Orders toEntity(OrdersResponseDto dto) {
        if (dto == null) {
            return null;
        }

        Orders orders = new Orders();
        orders.setId(dto.getId());
        orders.setProjects(dto.getProjects());
        orders.setDeveloper(UsersMapper.toObj(dto.getDeveloper()));
        orders.setPayments(PaymentsMapper.toEntity(dto.getPayments()));
        orders.setActive(dto.getActive());
        orders.setDateOfCreate(dto.getDateOfCreate());

        return orders;
    }
}
