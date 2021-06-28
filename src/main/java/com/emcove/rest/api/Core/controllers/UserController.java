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
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{id}")
    public Optional<User> getUser(@PathVariable Integer id){
        var user = userService.findUserById(id);
        if(user.isPresent()){
            return user;
        }
        return null;
    }

    @DeleteMapping("/{username}")
    public String deleteUser(@PathVariable String username){
        return "DeleteUser: " + username;
    }

    @PostMapping("/register")
    public ResponseEntity<String> createUser(@RequestBody User user){
        userService.createUser(user);
        return ResponseEntity.status(HttpStatus.OK).body("Usuario creado correctamente");
    }

    @GetMapping("/login")
    public ResponseEntity<User> login(){
        Optional<User> user = userRepository.findByUsername(userService.getLoggedUsername());
        if(user.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(user.get());
        }else
            throw new ResourceNotFoundException("No se encontro ningún usuario");

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
        try {
            return ResponseEntity.ok().body(userService.getReputation(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/reputation")
    public ResponseEntity<Reputation>  getMyReputation(){
        String username = userService.getLoggedUsername();
        Optional<User> user = userRepository.findByUsername(username);

        try {
            return ResponseEntity.ok().body(user.get().getReputation());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/myBusinessReputation")
    public ResponseEntity<Reputation>  getMyBusinessReputation(){
        String username = userService.getLoggedUsername();
        Optional<User> user = userRepository.findByUsername(username);

        try {
            return ResponseEntity.ok().body(user.get().getEntrepreneurship().getReputation());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
