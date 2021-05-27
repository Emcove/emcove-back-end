package com.emcove.rest.api.Core.service;

import com.emcove.rest.api.Core.response.Order;

public interface OrderService {
    void createOrder(Order order);
    void deleteOrder(Integer id);
    void updateOrder(Order order);
}
