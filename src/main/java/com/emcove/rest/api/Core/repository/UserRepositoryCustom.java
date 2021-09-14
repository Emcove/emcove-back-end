package com.emcove.rest.api.Core.repository;

import com.emcove.rest.api.Core.response.Order;
import com.emcove.rest.api.Core.response.OrderState;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@NoRepositoryBean
public interface UserRepositoryCustom {

    List<Order> findOrders(Integer userId);

    List<Order> findOrdersFilter(Integer userId, OrderState orderState, boolean asc);
}
