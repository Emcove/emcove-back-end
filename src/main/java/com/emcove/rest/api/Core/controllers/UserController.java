package com.emcove.rest.api.Core.controllers;

import com.emcove.rest.api.Core.dto.UserDTO;
import com.emcove.rest.api.Core.exception.ResourceNotFoundException;
import com.emcove.rest.api.Core.repository.UserRepository;
import com.emcove.rest.api.Core.response.Comment;
import com.emcove.rest.api.Core.response.Entrepreneurship;
import com.emcove.rest.api.Core.response.Reputation;
import com.emcove.rest.api.Core.response.User;
import com.emcove.rest.api.Core.service.UserService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.Optional;

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
}
