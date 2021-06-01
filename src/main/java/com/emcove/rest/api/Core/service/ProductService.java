package com.emcove.rest.api.Core.service;

import com.emcove.rest.api.Core.response.Product;

import java.util.Optional;

public interface ProductService {
    Product createProduct(Product product);
    Product updateProduct(Product product);
    void deleteProduct(Integer id);

    Optional<Product> findProductById(Integer id);
}
