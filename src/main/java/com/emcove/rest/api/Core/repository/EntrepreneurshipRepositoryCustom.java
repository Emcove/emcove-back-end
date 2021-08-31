package com.emcove.rest.api.Core.repository;

import com.emcove.rest.api.Core.response.Category;
import com.emcove.rest.api.Core.response.Entrepreneurship;
import com.emcove.rest.api.Core.response.Order;
import com.emcove.rest.api.Core.response.OrderState;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Transactional
@NoRepositoryBean
public interface EntrepreneurshipRepositoryCustom {
    List<Entrepreneurship> find(Set<Category> categories, String name, String productName);

    List<Order> findOrdersByEntrepreneurship(Integer entrepreneurshipId);

    List<Entrepreneurship> findBySubscriptionExpirationDate(Date currentTime);

    List<Order> findOrdersByEntrepreneurshipFilter(Integer entrepreneurshipId, OrderState orderState, boolean asc);
}
