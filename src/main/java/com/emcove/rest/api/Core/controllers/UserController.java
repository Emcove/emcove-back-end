package com.emcove.rest.api.Core.controllers;

import com.emcove.rest.api.Core.response.User;
import com.emcove.rest.api.Core.service.UserService;
import com.emcove.rest.api.Core.utilities.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public Optional<User> getUser(@PathVariable Integer id){
        Optional<User> user = userService.findUserById(id);
        if(user.isPresent()){
            return user.get();
        }
        return null;
    }

    @DeleteMapping("/{username}")
    public String deleteUser(@PathVariable String username){
        return "DeleteUser: " + username;
    }

    @PostMapping("/register")
    public String createUser(@RequestBody User user){
        Map<String,Object> response = new HashMap<>();
        try {
            userService.createUser(user);
        }catch (Exception e){
          
        }

        return "user create with success. user:" + ResponseUtils.toJson(user);
    }

    @GetMapping("/login")
    public ResponseEntity<String> login(){
        return ResponseEntity.status(HttpStatus.OK).body("messiii");
    }

}
