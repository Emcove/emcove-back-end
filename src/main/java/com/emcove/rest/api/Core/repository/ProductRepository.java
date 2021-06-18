package com.emcove.rest.api.Core.repository;

import com.emcove.rest.api.Core.response.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public interface ProductRepository extends JpaRepository<Product,Integer> {
}
