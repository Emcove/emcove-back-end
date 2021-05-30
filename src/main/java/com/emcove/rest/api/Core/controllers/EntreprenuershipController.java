package com.emcove.rest.api.Core.controllers;

import com.emcove.rest.api.Core.response.Entreprenuership;
import com.emcove.rest.api.Core.response.Product;
import com.emcove.rest.api.Core.response.Reputation;
import com.emcove.rest.api.Core.service.EntreprenuershipService;
import com.emcove.rest.api.Core.utilities.ResponseUtils;
import com.google.gson.JsonObject;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.management.ObjectName;
import java.lang.annotation.Repeatable;
import java.net.URI;
import java.util.*;

@RestController
@RequestMapping("/entrepreneurship")
public class EntreprenuershipController {
    @Autowired
    protected EntreprenuershipService entreprenuershipService;

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Entreprenuership>> getEntreprenuership(@PathVariable Integer id) {
        Optional<Entreprenuership> entreprenuership = entreprenuershipService.findEntreprenuershipById(id);

        if (entreprenuership.isPresent())
            return ResponseEntity.ok(entreprenuership);
        else
            return ResponseEntity.notFound().build();

    }

    @GetMapping("/{id}/products")
    public ResponseEntity<Set<Product>> getEntrepreneurshipProducts(@PathVariable Integer id){
        Optional<Entreprenuership> entrepreneurship = entreprenuershipService.findEntreprenuershipById(id);
        System.out.println(entrepreneurship.isPresent());
        if(entrepreneurship.isPresent())
            return ResponseEntity.ok().body(entrepreneurship.get().getProducts());
        else
            return ResponseEntity.notFound().build();

    }

    @GetMapping("/{id}/reputation")
    public ResponseEntity<Reputation> getEntrepreneurshipReputation(@PathVariable Integer id){
        Optional<Entreprenuership> entrepreneurship = entreprenuershipService.findEntreprenuershipById(id);
        System.out.println(entrepreneurship.isPresent());
        if(entrepreneurship.isPresent())
            return ResponseEntity.ok().body(entrepreneurship.get().getReputation());
        else
            return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Entreprenuership> deleteEntreprenuership(@PathVariable Integer id){
        entreprenuershipService.deleteEntreprenuership(id);
        return ResponseEntity.noContent().build();

    }

    @PostMapping()
    public ResponseEntity<Entreprenuership> createEntreprenuership(@RequestBody Entreprenuership entreprenuership){
        Entreprenuership newEntreprenuership = entreprenuershipService.createEntreprenuership(entreprenuership);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newEntreprenuership.getId()).toUri();

        return ResponseEntity.created(uri).body(newEntreprenuership);
    }

    @PutMapping("/{id}/product")
    public ResponseEntity<Entreprenuership> addEntrepreneurshipProduct(@PathVariable Integer id, @RequestBody Product product){
        Optional<Entreprenuership> entrepreneurship = entreprenuershipService.findEntreprenuershipById(id);
        System.out.println(entrepreneurship.isPresent());
        if(entrepreneurship.isPresent()){
            entrepreneurship.get().addProduct(product);
            entreprenuershipService.updateEntreprenuership(entrepreneurship.get());
            return ResponseEntity.ok().body(entrepreneurship.get());
        } else
            return ResponseEntity.notFound().build();

    }
}
