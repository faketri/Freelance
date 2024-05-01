package com.hivework.domain.service.orders;

import com.hivework.domain.entity.orders.Orders;
import com.hivework.domain.repository.OrdersRepository;
import org.apache.catalina.LifecycleState;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersService {

    private final OrdersRepository ordersRepository;

    public OrdersService(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    public Orders findById(Long id){
        return ordersRepository.findById(id).orElse(null);
    }

    public List<Orders> findAll(){
        return ordersRepository.findAll();
    }
}
