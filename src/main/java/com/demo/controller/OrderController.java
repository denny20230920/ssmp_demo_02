package com.demo.controller;

import com.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/{id}")
    public void dealOrder(@PathVariable String id){
        orderService.dealOrder(id);
    }

}
