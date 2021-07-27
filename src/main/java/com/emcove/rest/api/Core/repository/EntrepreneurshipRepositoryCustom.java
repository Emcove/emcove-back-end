package com.emcove.rest.api.Core.repository;

import com.emcove.rest.api.Core.response.Category;
import com.emcove.rest.api.Core.response.Entrepreneurship;
import com.emcove.rest.api.Core.response.Order;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Transactional
@NoRepositoryBean
public interface EntrepreneurshipRepositoryCustom {
    List<Entrepreneurship> find(Set<Category> categories, String name, String productName);

    List<Order> findOrders(Integer entrepreneurshipId);
}
