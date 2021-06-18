package com.emcove.rest.api.Core.controllers;

import com.emcove.rest.api.Core.response.Product;
import com.emcove.rest.api.Core.service.ProductService;
import com.emcove.rest.api.Core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer id){
        Optional<Product> product = productService.findProductById(id);
        if(product.isPresent())
            return ResponseEntity.ok(product.get());
        else
            return ResponseEntity.notFound().build();
    }
    @PostMapping()
    public ResponseEntity createProduct(@RequestBody Product product){
        try {
            productService.createProduct(product);
            return ResponseEntity.ok(product);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok().body(e.getMessage());
        }

    }
    @PutMapping("/{id}")
    public ResponseEntity putProduct(@RequestBody final Product product) {
        try {
            String loggedUsername = userService.getLoggedUsername();
            productService.updateProduct(product,loggedUsername);
            return ResponseEntity.ok().body(product);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

}
