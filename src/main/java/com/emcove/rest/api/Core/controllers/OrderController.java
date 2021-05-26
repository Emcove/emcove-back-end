package com.emcove.rest.api.Core.controllers;

import com.emcove.rest.api.Core.response.OrderState;
import com.emcove.rest.api.Core.response.Order;
import com.emcove.rest.api.Core.utilities.ResponseUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashSet;

@RestController
@RequestMapping("/order")
public class OrderController {

    @GetMapping("/{id}")
    public String getOrder(@PathVariable Integer id){
        LocalDate localDate = LocalDate.of(2021,5,25);
        Order order = new Order(id,localDate, null, OrderState.PENDIENTE,new HashSet<>());
        return ResponseUtils.toJson(order);
    }

    @DeleteMapping("/{id}")
    public String deleteOrder(@PathVariable Integer id){
        return "DeleteOrder: " + id;
    }

    @PostMapping("/")
    public String createOrder(@RequestParam String dateStr){
        Order order = new Order(1,ResponseUtils.getLocalDateFromString(dateStr), null, OrderState.PENDIENTE,new HashSet<>());
        return "Order create with success. user:" + ResponseUtils.toJson(order);
    }
}
