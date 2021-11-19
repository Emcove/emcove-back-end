package com.emcove.rest.api.Core.repository;

import com.emcove.rest.api.Core.response.Order;
import com.emcove.rest.api.Core.response.OrderState;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryCustomImpl implements UserRepositoryCustom{
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<Order> findOrders(String username) {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT DISTINCT ordr from Order as ordr ");
        sb.append("WHERE ordr.username = :username ");


        Query query = entityManager.createQuery(sb.toString());
        query.setParameter("username",username);

        try {
            return query.getResultList();
        }catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public List<Order> findOrdersFilter(String username, OrderState orderState) {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT DISTINCT ordr from Order as ordr ");
        sb.append("WHERE ordr.username = :username ");
        if(orderState != null)
            sb.append("AND ordr.currentState = :orderState ");


        Query query = entityManager.createQuery(sb.toString());
        query.setParameter("username",username);

        if(orderState != null)
            query.setParameter("orderState",orderState);

        try {
            return query.getResultList();
        }catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
