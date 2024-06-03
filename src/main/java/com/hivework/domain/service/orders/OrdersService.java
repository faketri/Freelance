package com.hivework.domain.service.orders;

import com.hivework.domain.entity.orders.Orders;
import com.hivework.domain.repository.OrdersRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class OrdersService {

    private final OrdersRepository ordersRepository;

    public OrdersService(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    public Orders findById(Long id) {
        return ordersRepository.findById(id).orElse(null);
    }

    public Page<Orders> findAll(Pageable pageable) {
        return ordersRepository.findAll(pageable);
    }

    public Orders save(Orders entity) {
        return ordersRepository.save(entity);
    }
}
