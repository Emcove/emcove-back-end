package com.emcove.rest.api.Core.repository;

import com.emcove.rest.api.Core.response.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Integer> {
}
