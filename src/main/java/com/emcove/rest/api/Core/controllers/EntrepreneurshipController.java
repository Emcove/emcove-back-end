package com.emcove.rest.api.Core.controllers;

import com.emcove.rest.api.Core.dto.EntrepreneurshipDTO;
import com.emcove.rest.api.Core.response.Comment;
import com.emcove.rest.api.Core.response.Entrepreneurship;
import com.emcove.rest.api.Core.response.Product;
import com.emcove.rest.api.Core.response.Reputation;
import com.emcove.rest.api.Core.response.User;
import com.emcove.rest.api.Core.service.EntrepreneurshipService;
import com.emcove.rest.api.Core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.*;

@RestController
@RequestMapping("/entrepreneurships")
public class EntrepreneurshipController {
    @Autowired
    protected EntrepreneurshipService entrepreneurshipService;
    @Autowired
    protected UserService userService;

    @GetMapping()
    public ResponseEntity<List<Entrepreneurship>> getAll() {
        return ResponseEntity.ok().body(entrepreneurshipService.findAll());

    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Entrepreneurship>> getEntrepreneurship(@PathVariable Integer id) {
        Optional<Entrepreneurship> entreprenuership = entrepreneurshipService.findEntrepreneurshipById(id);

        if (entreprenuership.isPresent())
            return ResponseEntity.ok(entreprenuership);
        else
            return ResponseEntity.notFound().build();

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Entrepreneurship> deleteEntrepreneurship(@PathVariable Integer id){
        entrepreneurshipService.deleteEntrepreneurship(id);
        return ResponseEntity.noContent().build();

    }
    @PostMapping()
    public ResponseEntity createEntrepreneurship(@RequestBody Entrepreneurship entrepreneurship){
        try {
            String loggedUsername = userService.getLoggedUsername();
            User user = userService.createEntrepreneurship(loggedUsername, entrepreneurship);
            final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getEntrepreneurship().getId()).toUri();
            return ResponseEntity.created(uri).body(user.getEntrepreneurship());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PutMapping()
    public ResponseEntity<Entrepreneurship> updateEnteEntrepreneurship(@RequestBody Entrepreneurship entrepreneurship){

        if(entrepreneurship.getId() == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        else
            return  ResponseEntity.ok().body(entrepreneurshipService.updateEntrepreneurship(entrepreneurship));
    }
    @PatchMapping("/{id}")
    public ResponseEntity<Entrepreneurship> patchEntrepreneurship(@PathVariable Integer id, @RequestBody EntrepreneurshipDTO entrepreneurshipDTO){
           return ResponseEntity.ok().body(entrepreneurshipService.patchEntrepreneurship(id,entrepreneurshipDTO));
    }
    @GetMapping("/{id}/products")
    public ResponseEntity<Set<Product>> getEntrepreneurshipProducts(@PathVariable Integer id){
        Optional<Entrepreneurship> entrepreneurship = entrepreneurshipService.findEntrepreneurshipById(id);
        if(entrepreneurship.isPresent())
            return ResponseEntity.ok().body(entrepreneurship.get().getProducts());
        else
            return ResponseEntity.notFound().build();

    }

    @GetMapping("/{id}/reputation")
    public ResponseEntity<Reputation> getEntrepreneurshipReputation(@PathVariable Integer id){
        try {
            return ResponseEntity.ok().body(entrepreneurshipService.getReputation(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }


    @PostMapping("/{id}/product")
    public ResponseEntity addEntrepreneurshipProduct(@PathVariable Integer id, @RequestBody Product product){
        Optional<Entrepreneurship> entrepreneurship = entrepreneurshipService.findEntrepreneurshipById(id);
        if(entrepreneurship.isPresent()){
            try {
                return ResponseEntity.ok().body(entrepreneurshipService.addProduct(entrepreneurship.get(), product));
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        } else
            return ResponseEntity.notFound().build();

    }
    @PostMapping("/{id}/reputation/comment")
    public ResponseEntity<Reputation>  createComment(@PathVariable Integer id, @RequestBody Comment comment){
        try {
            Reputation reputation = entrepreneurshipService.addComment(id, comment);
            return ResponseEntity.ok().body(reputation);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }


}
