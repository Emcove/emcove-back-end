package com.emcove.rest.api.Core.serviceimp;

import com.emcove.rest.api.Core.repository.ProductRepository;
import com.emcove.rest.api.Core.repository.UserRepository;
import com.emcove.rest.api.Core.response.Entrepreneurship;
import com.emcove.rest.api.Core.response.Product;
import com.emcove.rest.api.Core.response.User;
import com.emcove.rest.api.Core.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public Product createProduct(Product product) throws Exception {

        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product, String loggedUsername) throws Exception {
        Optional<User> user = userRepository.findByUsername(loggedUsername);
        if(user.isPresent()){
            Entrepreneurship entrepreneurship = user.get().getEntrepreneurship();
            if(entrepreneurship == null)
                throw new IllegalAccessException("El usuario logueado no posee ningún emprendimiento.");
            if(!entrepreneurship.hasProduct(product))
                throw new IllegalAccessException("El producto no corresponde al emprendimiento del usuario logueado.");

            return productRepository.save(product);
        }
        throw new Exception("Usuario no encontrado");

    }


    @Override
    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }

    @Override
    public Optional<Product> findProductById(Integer id) {
        return productRepository.findById(id);
    }
}
