package com.hivework.domain.controller;

import com.hivework.domain.entity.orders.Orders;
import com.hivework.domain.service.orders.OrdersService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin({"http://localhost:8080", "http://192.168.1.106:8080/"})
@RequestMapping("/api/v1/orders")
public class OrdersController {

    private final OrdersService ordersService;

    public OrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Orders> findAll(){
        return ordersService.findAll();
    }
}
