package com.emcove.rest.api.Core.repository;

import com.emcove.rest.api.Core.response.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.OptionalDouble;

@Transactional
public interface OrderRepository extends JpaRepository<Order,Integer> {
    Optional<Order> findById(Integer id);
}
