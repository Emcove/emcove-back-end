package com.emcove.rest.api.Core.repository;

import com.emcove.rest.api.Core.response.Category;
import com.emcove.rest.api.Core.response.Entrepreneurship;
import com.emcove.rest.api.Core.response.Order;
import com.emcove.rest.api.Core.response.OrderState;
import com.emcove.rest.api.Core.response.Product;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Transactional
@NoRepositoryBean
public interface EntrepreneurshipRepositoryCustom {
    List<Entrepreneurship> find(Set<Category> categories, String name, String productName);

    List<Order> findOrdersByEntrepreneurship(String entrepreneurshipName);

    List<Entrepreneurship> findBySubscriptionExpirationDate(Date currentTime);

    List<Order> findOrdersByEntrepreneurshipFilter(String entrepreneurshipName, OrderState orderState);

    Set<Product> findProductsByEntrepreneurship(Integer entrepreneurshipId);
}
