package com.emcove.rest.api.Core.controllers;

import com.emcove.rest.api.Core.dto.EntrepreneurshipDTO;
import com.emcove.rest.api.Core.dto.SubscriptionPlanDTO;
import com.emcove.rest.api.Core.response.Category;
import com.emcove.rest.api.Core.response.Comment;
import com.emcove.rest.api.Core.response.Entrepreneurship;
import com.emcove.rest.api.Core.response.Product;
import com.emcove.rest.api.Core.response.Reputation;
import com.emcove.rest.api.Core.response.User;
import com.emcove.rest.api.Core.service.EntrepreneurshipService;
import com.emcove.rest.api.Core.service.UserService;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Preference;
import com.mercadopago.resources.datastructures.preference.BackUrls;
import com.mercadopago.resources.datastructures.preference.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.*;


@RestController
@CrossOrigin("${spring.config.env.crossOrigin}")
@RequestMapping("/entrepreneurships")
public class EntrepreneurshipController {
    @Autowired
    protected EntrepreneurshipService entrepreneurshipService;
    @Autowired
    protected UserService userService;

    @GetMapping()
    public ResponseEntity<List<Entrepreneurship>> getAll(@RequestParam(required = false) Set<Category> categories, @RequestParam(required = false) String name, @RequestParam(required = false) String productName) {
        return ResponseEntity.ok().body(entrepreneurshipService.findAll(categories,name,productName));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Entrepreneurship> getEntrepreneurship(@PathVariable Integer id) {
        return ResponseEntity.ok(entrepreneurshipService.findEntrepreneurshipById(id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Entrepreneurship> deleteEntrepreneurship(@PathVariable Integer id){
        entrepreneurshipService.deleteEntrepreneurship(id);
        return ResponseEntity.noContent().build();

    }
    @PostMapping()
    public ResponseEntity<Entrepreneurship> createEntrepreneurship(@Valid @RequestBody Entrepreneurship entrepreneurship) {
        //TODO: Ver por que cuando se manda un nombre de emprendimiento igual tira "Unable to access lob stream"

        String loggedUsername = userService.getLoggedUsername();
        User user = userService.createEntrepreneurship(loggedUsername, entrepreneurship);
        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getEntrepreneurship().getId()).toUri();
        return ResponseEntity.created(uri).body(user.getEntrepreneurship());
    }

    @PutMapping()
    public ResponseEntity<Entrepreneurship> updateEnteEntrepreneurship(@RequestBody Entrepreneurship entrepreneurship){
        return  ResponseEntity.ok().body(entrepreneurshipService.updateEntrepreneurship(entrepreneurship));
    }
    @PatchMapping("/{id}")
    public ResponseEntity<Entrepreneurship> patchEntrepreneurship(@PathVariable Integer id, @RequestBody EntrepreneurshipDTO entrepreneurshipDTO){
           return ResponseEntity.ok().body(entrepreneurshipService.patchEntrepreneurship(id,entrepreneurshipDTO));
    }
    @GetMapping("/{id}/products")
    public ResponseEntity<Set<Product>> getEntrepreneurshipProducts(@PathVariable Integer id){
        return ResponseEntity.ok().body(entrepreneurshipService.findEntrepreneurshipById(id).getProducts());
    }

    /**
     * Trae la reputacion de un emprendiemiento
     * @param id id del emprendimiento que se desea obtener la reputacion
     * @return Devuelve una reputacion
     */
    @GetMapping("/{id}/reputation")
    public ResponseEntity<Reputation> getEntrepreneurshipReputation(@PathVariable Integer id){
        return ResponseEntity.ok().body(entrepreneurshipService.getReputation(id));
    }

    /**
     * Trae reputacion del emprendimiento del usuario logueado
     */
    @GetMapping("/reputation")
    public ResponseEntity<Reputation> getLoggedEntrepreneurshipReputation(){
        return ResponseEntity.ok().body(entrepreneurshipService.getReputationByUsername(userService.getLoggedUsername()));
    }

    @PostMapping("/{id}/product")
    public ResponseEntity<Entrepreneurship> addEntrepreneurshipProduct(@PathVariable Integer id,@Valid @RequestBody Product product){
        return ResponseEntity.ok().body(entrepreneurshipService.addProduct(id, product));
    }
    @PostMapping("/{id}/reputation/comment")
    public ResponseEntity<Reputation>  createComment(@PathVariable Integer id, @RequestBody Comment comment){
        return ResponseEntity.ok().body(entrepreneurshipService.addComment(id, comment));
    }

    @PostMapping("/{id}/subscriptions")
    public ResponseEntity createSubscription(@PathVariable Integer id, @RequestBody SubscriptionPlanDTO plan){
        entrepreneurshipService.subscribe(id, plan);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/subscriptions")
    public ResponseEntity<List<Map<String, String>>> getSubscription() throws MPException {
        List<Float> values = new ArrayList<>();
        values.add(1.0f);
        values.add(5.0f);
        values.add(10.0f);

        List<Map<String, String>> result = new ArrayList<>();
        String baseURL = System.getenv("BASE_URL");
        for (Float value : values) {
            String subscriptionTitle = getSubscriptionTitle(value);
            Preference preference = new Preference();

            BackUrls backUrls = new BackUrls(
                    baseURL + "/#/business?from=nav-header",
                    baseURL+ "/#/business?from=nav-header",
                    baseURL + "/#/business?from=nav-header");
            preference.setBackUrls(backUrls);

            Item item = new Item();
            item.setTitle(subscriptionTitle)
                    .setQuantity(1)
                    .setUnitPrice(value);
            preference.appendItem(item);
            preference.save();

            Map<String, String> mpPref = new HashMap<>();
            mpPref.put("id", preference.getId());
            mpPref.put("price", value.toString());
            mpPref.put("sandbox_init_point", preference.getSandboxInitPoint());
            mpPref.put("init_point", preference.getInitPoint());
            mpPref.put("title", subscriptionTitle);

            switch (value.toString()) {
                case "1.0":
                    mpPref.put("plan", "month");
                    break;
                case "5.0":
                    mpPref.put("plan", "6-month");
                    break;
                case "10.0":
                    mpPref.put("plan", "annual");
                    break;
            }

            result.add(mpPref);
        }

        return ResponseEntity.ok(result);
    }

    private String getSubscriptionTitle(Float value) {
        switch (value.toString()) {
            case "1.0":
                return "Suscripción mensual";
            case "5.0":
                return "6 meses";
            case "10.0":
                return "Suscripción anual";
        }

        return "Suscripción";
    }
}
