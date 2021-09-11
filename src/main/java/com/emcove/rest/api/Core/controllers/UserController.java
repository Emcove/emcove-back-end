package com.emcove.rest.api.Core.controllers;

import com.emcove.rest.api.Core.dto.UserDTO;
import com.emcove.rest.api.Core.response.Comment;
import com.emcove.rest.api.Core.response.DeliveryPoint;
import com.emcove.rest.api.Core.response.Order;
import com.emcove.rest.api.Core.response.OrderState;
import com.emcove.rest.api.Core.response.Reputation;
import com.emcove.rest.api.Core.response.User;
import com.emcove.rest.api.Core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("${spring.config.env.crossOrigin}")
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Integer id){
        return ResponseEntity.ok(userService.findUserById(id));
    }

    @DeleteMapping("")
    public ResponseEntity<String> deleteUser(){
        userService.deleteUser(userService.getLoggedUsername());

        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity<String> createUser(@RequestBody User user){
        userService.createUser(user);
        return ResponseEntity.status(HttpStatus.OK).body("Usuario creado correctamente");
    }

    @GetMapping("/login")
    public ResponseEntity<User> login(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getLoggedUser());
    }

    @PatchMapping("/update")
    public ResponseEntity<User> patchUser(@RequestBody UserDTO userDTO){
        return ResponseEntity.ok().body(userService.patchUser(userDTO));
    }


    @PostMapping("/{id}/reputation/comment")
    public ResponseEntity<Reputation>  createComment(@PathVariable Integer id, @RequestBody Comment comment){
        Reputation reputation = userService.addComment(id, comment);
        return ResponseEntity.ok().body(reputation);
    }

    @GetMapping("/{id}/reputation")
    public ResponseEntity<Reputation>  getReputation(@PathVariable Integer id){
        return ResponseEntity.ok().body(userService.getReputation(id));
    }

    @GetMapping("/reputation")
    public ResponseEntity<Reputation>  getMyReputation(){
        return ResponseEntity.ok().body(userService.getReputationByUsername(userService.getLoggedUsername()));
    }

    @GetMapping("/orders")
    public ResponseEntity<List<Order>>  getOrders(){
        return ResponseEntity.ok().body(userService.getOrders(userService.getLoggedUsername()));
    }

    @PostMapping("/orders/{orderId}/cancel")
    public ResponseEntity<Order>  addOrderTrackingToOrder(@PathVariable Integer orderId) throws IllegalAccessException {
        return ResponseEntity.ok().body(userService.cancelOrder(orderId,userService.getLoggedUsername()));
    }

    @PostMapping("/deliveryPoints")
    public ResponseEntity<User>  addDeliveryPoint(@RequestBody DeliveryPoint deliveryPoint) throws IllegalAccessException {
        return ResponseEntity.ok().body(userService.addDeliveryPoint(userService.getLoggedUsername(), deliveryPoint));
    }

}
