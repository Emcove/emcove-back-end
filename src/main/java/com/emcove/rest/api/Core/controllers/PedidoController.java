package com.emcove.rest.api.Core.controllers;

import com.emcove.rest.api.Core.response.EstadoPedido;
import com.emcove.rest.api.Core.response.Pedido;
import com.emcove.rest.api.Core.response.Usuario;
import com.emcove.rest.api.Core.utilities.ResponseUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashSet;

@RestController
@RequestMapping("/order")
public class PedidoController {

    @GetMapping("/{id}")
    public String getOrder(@PathVariable Integer id){
        LocalDate localDate = LocalDate.of(2021,5,25);
        Pedido order = new Pedido(id,localDate, null, EstadoPedido.PENDIENTE,new HashSet<>());
        return ResponseUtils.toJson(order);
    }

    @DeleteMapping("/{id}")
    public String deleteOrder(@PathVariable Integer id){
        return "DeleteOrder: " + id;
    }

    @PostMapping("/")
    public String createOrder(@RequestParam String dateStr){
        Pedido order = new Pedido(1,ResponseUtils.getLocalDateFromString(dateStr), null, EstadoPedido.PENDIENTE,new HashSet<>());
        return "Order create with success. user:" + ResponseUtils.toJson(order);
    }
}
