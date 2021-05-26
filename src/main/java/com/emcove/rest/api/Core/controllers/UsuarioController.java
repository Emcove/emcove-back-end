package com.emcove.rest.api.Core.controllers;

import com.emcove.rest.api.Core.response.Usuario;
import com.emcove.rest.api.Core.utilities.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsuarioController {

    @GetMapping("/{username}")
    public String getUser(@PathVariable String username){
        Usuario user = new Usuario(username,"12345678","test1@yopmail.com");
        return ResponseUtils.toJson(user);
    }

    @DeleteMapping("/{username}")
    public String deleteUser(@PathVariable String username){
        return "DeleteUser: " + username;
    }

    @PostMapping("/")
    public String createUser(@RequestParam String username, @RequestParam String password, @RequestParam String email){
        Usuario user = new Usuario(username, password, email);

        return "user create with success. user:" + ResponseUtils.toJson(user);
    }

}
