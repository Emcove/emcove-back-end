package com.emcove.rest.api.Core.controllers;

import com.emcove.rest.api.Core.dto.EntrepreneurshipDTO;
import com.emcove.rest.api.Core.response.Entreprenuership;
import com.emcove.rest.api.Core.response.Product;
import com.emcove.rest.api.Core.response.Reputation;
import com.emcove.rest.api.Core.service.EntreprenuershipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.*;

@RestController
@RequestMapping("/entrepreneurship")
public class EntreprenuershipController {
    @Autowired
    protected EntreprenuershipService entreprenuershipService;
    @GetMapping()
    public ResponseEntity<List<Entreprenuership>> getAll() {
        return ResponseEntity.ok().body(entreprenuershipService.findAll());

    }
    @PostMapping()
    public ResponseEntity<Entreprenuership> createEntreprenuership(@RequestBody Entreprenuership entreprenuership){
        Entreprenuership newEntreprenuership = entreprenuershipService.createEntreprenuership(entreprenuership);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newEntreprenuership.getId()).toUri();

        return ResponseEntity.created(uri).body(newEntreprenuership);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Entreprenuership>> getEntreprenuership(@PathVariable Integer id) {
        Optional<Entreprenuership> entreprenuership = entreprenuershipService.findEntreprenuershipById(id);

        if (entreprenuership.isPresent())
            return ResponseEntity.ok(entreprenuership);
        else
            return ResponseEntity.notFound().build();

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Entreprenuership> deleteEntreprenuership(@PathVariable Integer id){
        entreprenuershipService.deleteEntreprenuership(id);
        return ResponseEntity.noContent().build();

    }

    @PutMapping()
    public ResponseEntity<Entreprenuership> updateEnteEntreprenuership(@RequestBody Entreprenuership entreprenuership){

        if(entreprenuership.getId() == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        else
            return  ResponseEntity.ok().body(entreprenuershipService.updateEntreprenuership(entreprenuership));
    }
    @PatchMapping("/{id}")
    public ResponseEntity<Entreprenuership> patchEnteResponse(@PathVariable Integer id, @RequestBody EntrepreneurshipDTO entrepreneurshipDTO){
           return ResponseEntity.ok().body(entreprenuershipService.patchEntrepreneurship(id,entrepreneurshipDTO));
    }
    @GetMapping("/{id}/products")
    public ResponseEntity<Set<Product>> getEntrepreneurshipProducts(@PathVariable Integer id){
        Optional<Entreprenuership> entrepreneurship = entreprenuershipService.findEntreprenuershipById(id);
        if(entrepreneurship.isPresent())
            return ResponseEntity.ok().body(entrepreneurship.get().getProducts());
        else
            return ResponseEntity.notFound().build();

    }

    @GetMapping("/{id}/reputation")
    public ResponseEntity<Reputation> getEntrepreneurshipReputation(@PathVariable Integer id){
        Optional<Entreprenuership> entrepreneurship = entreprenuershipService.findEntreprenuershipById(id);
        if(entrepreneurship.isPresent())
            return ResponseEntity.ok().body(entrepreneurship.get().getReputation());
        else
            return ResponseEntity.notFound().build();

    }


    @PostMapping("/{id}/product")
    public ResponseEntity<Entreprenuership> addEntrepreneurshipProduct(@PathVariable Integer id, @RequestBody Product product){
        Optional<Entreprenuership> entrepreneurship = entreprenuershipService.findEntreprenuershipById(id);
        if(entrepreneurship.isPresent()){
            return ResponseEntity.ok().body(entreprenuershipService.addProduct(entrepreneurship.get(), product));
        } else
            return ResponseEntity.notFound().build();

    }
}
