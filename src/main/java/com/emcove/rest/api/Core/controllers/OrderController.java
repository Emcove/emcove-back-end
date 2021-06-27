package com.emcove.rest.api.Core.controllers;

import com.emcove.rest.api.Core.response.OrderState;
import com.emcove.rest.api.Core.response.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable Integer id){
        LocalDate localDate = LocalDate.of(2021,5,25);
        Order order = new Order(id,localDate, null, OrderState.PENDIENTE,null);
        return ResponseEntity.ok().body(order);
    }

    @DeleteMapping("/{id}")
    public String deleteOrder(@PathVariable Integer id){
        return "DeleteOrder: " + id;
    }

    @PostMapping()
    public ResponseEntity<Order> createOrder(@RequestBody Order order){

        return ResponseEntity.ok().body(order);
    }
}
