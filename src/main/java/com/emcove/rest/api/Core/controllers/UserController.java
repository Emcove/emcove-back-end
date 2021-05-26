package com.emcove.rest.api.Core.controllers;

import com.emcove.rest.api.Core.response.User;
import com.emcove.rest.api.Core.utilities.ResponseUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/{username}")
    public String getUser(@PathVariable String username){
        User user = new User(username,"12345678","test1@yopmail.com");
        return ResponseUtils.toJson(user);
    }

    @DeleteMapping("/{username}")
    public String deleteUser(@PathVariable String username){
        return "DeleteUser: " + username;
    }

    @PostMapping("/")
    public String createUser(@RequestParam String username, @RequestParam String password, @RequestParam String email){
        User user = new User(username, password, email);

        return "user create with success. user:" + ResponseUtils.toJson(user);
    }

}
