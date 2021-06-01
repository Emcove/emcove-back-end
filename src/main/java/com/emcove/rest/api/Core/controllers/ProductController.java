package com.emcove.rest.api.Core.controllers;

import com.emcove.rest.api.Core.response.Product;
import com.emcove.rest.api.Core.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.sound.sampled.Port;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer id){
        Optional<Product> product = productService.findProductById(id);
        if(product.isPresent())
            return ResponseEntity.ok(product.get());
        else
            return ResponseEntity.notFound().build();
    }
    @PostMapping()
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        productService.createProduct(product);
        return ResponseEntity.ok(product);
    }

}
