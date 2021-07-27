package com.emcove.rest.api.Core.repository;

import com.emcove.rest.api.Core.response.Order;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@NoRepositoryBean
public interface UserRepositoryCustom {

    List<Order> findOrders(Integer userId);
}
