package com.emcove.rest.api.Core.service;

import com.emcove.rest.api.Core.response.Product;

import javax.validation.constraints.NotNull;
import java.util.Optional;

public interface ProductService {
    Product createProduct(Product product) throws Exception;
    Product updateProduct(Product product, String loggedUsername) throws Exception;
    void deleteProduct(Integer id);

    Optional<Product> findProductById(Integer id);

    void validateProduct(@NotNull Product product) throws Exception;
}
