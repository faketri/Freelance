package com.hivework.domain.controller;

import com.hivework.domain.dto.response.OrdersResponseDto;
import com.hivework.domain.mapper.OrdersMapper;
import com.hivework.domain.service.orders.OrdersService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin({"http://localhost:8080", "http://192.168.1.106:8080/"})
@RequestMapping("/api/v1/orders")
public class OrdersController {

    private final OrdersService ordersService;

    public OrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<OrdersResponseDto> findAll(final @RequestParam(name = "number", required = true, defaultValue = "0") Integer pageNumber,
                                           final @RequestParam(name = "size", required = true, defaultValue = "20") Integer pageSize) {
        return ordersService.findAll(PageRequest.of(pageNumber, pageSize)).map(OrdersMapper::toDto);
    }
}
