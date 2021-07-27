package com.emcove.rest.api.Core.repository;

import com.emcove.rest.api.Core.response.Order;
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
    public List<Order> findOrders(Integer userId) {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT DISTINCT ordr from Order as ordr ");
        sb.append("INNER JOIN ordr.user as usr ");
        sb.append("WHERE usr.id = :userId");

        Query query = entityManager.createQuery(sb.toString());
        query.setParameter("userId",userId);

        try {
            return query.getResultList();
        }catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
