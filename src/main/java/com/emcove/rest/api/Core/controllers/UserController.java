package com.emcove.rest.api.Core.controllers;

import com.emcove.rest.api.Core.dto.EntrepreneurshipDTO;
import com.emcove.rest.api.Core.dto.UserDTO;
import com.emcove.rest.api.Core.repository.UserRepository;
import com.emcove.rest.api.Core.response.Entrepreneurship;
import com.emcove.rest.api.Core.response.User;
import com.emcove.rest.api.Core.service.UserService;
import com.emcove.rest.api.Core.utilities.ResponseUtils;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
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
        Gson gson = new Gson();
        System.out.println(gson.toJson(user));
        try {
            userService.createUser(user);
            return ResponseEntity.status(HttpStatus.OK).body("Usuario creado correctamente");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/login")
    public ResponseEntity<User> login(){
        Optional<User> user = userRepository.findByUsername(userService.getLoggedUsername());

        if(user.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(user.get());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @PatchMapping("/update")
    public ResponseEntity<User> patchUser(@RequestBody UserDTO userDTO){
        return ResponseEntity.ok().body(userService.patchUser(userDTO));
    }

}
