package com.emcove.rest.api.Core.controllers;

import com.emcove.rest.api.Core.dto.SubscriptionPlanDTO;
import com.emcove.rest.api.Core.response.Category;
import com.emcove.rest.api.Core.response.Comment;
import com.emcove.rest.api.Core.response.DeliveryPoint;
import com.emcove.rest.api.Core.response.Entrepreneurship;
import com.emcove.rest.api.Core.response.Order;
import com.emcove.rest.api.Core.response.OrderState;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;


@RestController
@CrossOrigin("${spring.config.env.crossOrigin}")
@RequestMapping("/entrepreneurships")
public class EntrepreneurshipController {
    @Autowired
    protected EntrepreneurshipService entrepreneurshipService;
    @Autowired
    protected UserService userService;

    @GetMapping()
    public ResponseEntity<List<Entrepreneurship>> getAll(@RequestParam(required = false) Set<Category> categories,
                                                         @RequestParam(required = false) String name, @RequestParam(required = false) String productName) {
        List<Entrepreneurship> entrepreneurships = entrepreneurshipService.findAll(categories, name, productName);
        entrepreneurships.stream().map(e -> {
            e.setProducts(null);
            e.setDeliveryPoints(null);
            e.getReputation().setComments(new ArrayList<>());
            return e;
        }).collect(Collectors.toList());
        return ResponseEntity.ok().body(entrepreneurships);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Entrepreneurship> getEntrepreneurship(@PathVariable Integer id) {
        return ResponseEntity.ok(entrepreneurshipService.findEntrepreneurshipById(id));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Entrepreneurship> getEntrepreneurshipByName(@PathVariable String name) {
        return ResponseEntity.ok(entrepreneurshipService.findEntrepreneurshipByName(name));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Entrepreneurship> deleteEntrepreneurship(@PathVariable Integer id){
        entrepreneurshipService.deleteEntrepreneurship(id);
        return ResponseEntity.noContent().build();

    }

    @PostMapping()
    public ResponseEntity<Entrepreneurship> createEntrepreneurship(@Valid @RequestBody Entrepreneurship entrepreneurship) {
        String loggedUsername = userService.getLoggedUsername();
        User user = userService.createEntrepreneurship(loggedUsername, entrepreneurship);
        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getEntrepreneurship().getId()).toUri();
        return ResponseEntity.created(uri).body(user.getEntrepreneurship());
    }

    @PutMapping()
    public ResponseEntity<Entrepreneurship> updateEntrepreneurship(@RequestBody Entrepreneurship entrepreneurship){
        return  ResponseEntity.ok().body(entrepreneurshipService.updateEntrepreneurship(entrepreneurship));
    }

    @PatchMapping()
    public ResponseEntity<Entrepreneurship> patchEntrepreneurship(@RequestBody Entrepreneurship entrepreneurship){
        if(entrepreneurship.getId().equals(entrepreneurshipService.getEntrepreneurshipByUsername(userService.getLoggedUsername()).getId())){
            return ResponseEntity.ok().body(entrepreneurshipService.patchEntrepreneurship(entrepreneurship));
        }
        return new ResponseEntity<>(entrepreneurship, HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/{id}/products")
    public ResponseEntity<Set<Product>> getEntrepreneurshipProducts(@PathVariable("id") Integer entrepreneurshipId){
        return ResponseEntity.ok().body(entrepreneurshipService.findEntrepreneurshipProducts(entrepreneurshipId));
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

    /**
     * Trae emprendimiento del usuario logueado
     */
    @GetMapping("/logged")
    public ResponseEntity<Entrepreneurship> getLoggedEntrepreneurship(){
        return ResponseEntity.ok().body(entrepreneurshipService.getEntrepreneurshipByUsername(userService.getLoggedUsername()));
    }


    @PostMapping("/{id}/product")
    public ResponseEntity<Entrepreneurship> addEntrepreneurshipProduct(@PathVariable Integer id,@Valid @RequestBody Product product){
        return ResponseEntity.ok().body(entrepreneurshipService.addProduct(id, product));
    }

    @PostMapping("/{id}/reputation/comment")
    public ResponseEntity<Reputation>  createComment(@PathVariable Integer id, @RequestBody Comment comment){
        return ResponseEntity.ok().body(entrepreneurshipService.addComment(id, comment));
    }
    @PostMapping("/{id}/order")
    public ResponseEntity<Order>  createOrder(@PathVariable Integer id, @RequestBody Order order){
        return ResponseEntity.ok().body(entrepreneurshipService.addOrder(id,order,userService.getLoggedUsername()));
    }

    @GetMapping("/orders")
    public ResponseEntity<List<Order>>  getOrders(@RequestParam(required = false) OrderState orderState){
        List<Order> orders = entrepreneurshipService.getOrders(userService.getLoggedUsername(), orderState);
        /*orders.stream().map(o ->  {
            //o.setProduct(null);
           // o.getEntrepreneurship().setProducts(null);
            //o.getUser().setEntrepreneurship(null);
            return o;
        }).collect(Collectors.toList());*/
        return ResponseEntity.ok().body(orders);
    }

    @PostMapping("/orders/{orderId}/orderTracking")
    public ResponseEntity<Order>  addOrderTrackingToOrder(@PathVariable Integer orderId, @RequestParam OrderState newOrderState, @RequestParam(required = false) Integer deliveryPointId, @RequestParam(required = false) String closeReason) throws IllegalAccessException {
        return ResponseEntity.ok().body(entrepreneurshipService.addOrderTrackingToOrder(orderId,newOrderState,userService.getLoggedUsername(),deliveryPointId, closeReason));
    }

    @PostMapping("/deliveryPoints")
    public ResponseEntity<Entrepreneurship> addDeliveryPoint(@RequestBody DeliveryPoint deliveryPoint)  {
        return ResponseEntity.ok().body(entrepreneurshipService.addDeliveryPoint(userService.getLoggedUsername(), deliveryPoint));
    }

    @PostMapping("/{id}/subscriptions")
    public ResponseEntity<Entrepreneurship> createSubscription(@PathVariable Integer id, @RequestBody SubscriptionPlanDTO plan){
        entrepreneurshipService.subscribe(id, plan);
        return ResponseEntity.ok().body(entrepreneurshipService.findEntrepreneurshipById(id));
    }

    @GetMapping("/subscriptions")
    public ResponseEntity<List<Map<String, String>>> getSubscription(@RequestParam String businessName) throws MPException {
        List<Float> values = new ArrayList<>();
        values.add(300.0f);
        values.add(1500.0f);
        values.add(2400.0f);

        List<Map<String, String>> result = new ArrayList<>();
        String baseURL = System.getenv("BASE_URL");
        for (Float value : values) {
            String subscriptionTitle = getSubscriptionTitle(value);
            String subscriptionName = getSubscriptionPlan(value);
            Preference preference = new Preference();

            BackUrls backUrls = new BackUrls(
                    baseURL + "/#/business/" + businessName + "?from=nav-header&plan=" + subscriptionName,
                    baseURL+ "/#/business/" + businessName +"?from=nav-header&plan=" + subscriptionName,
                    baseURL + "/#/business/" + businessName + "?from=nav-header&plan=" + subscriptionName);
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
            mpPref.put("plan", subscriptionName);

            result.add(mpPref);
        }

        return ResponseEntity.ok(result);
    }



    private String getSubscriptionTitle(Float value) {
        switch (value.toString()) {
            case "300.0":
                return "Suscripci??n mensual";
            case "1500.0":
                return "6 meses";
            case "2400.0":
                return "Suscripci??n anual";
        }

        return "Suscripci??n";
    }

    private String getSubscriptionPlan(Float value) {
        switch (value.toString()) {
            case "300.0":
                return "month";
            case "1500.0":
                return "6-month";
            case "2400.0":
                return "annual";
        }
        return "";
    }

    @PostMapping("/{id}/calendar")
    public ResponseEntity<Entrepreneurship> registerCalendar(@PathVariable Integer id, @RequestParam String calendarId){
        entrepreneurshipService.registerCalendar(id, calendarId);
        return ResponseEntity.ok().body(entrepreneurshipService.findEntrepreneurshipById(id));
    }
}
