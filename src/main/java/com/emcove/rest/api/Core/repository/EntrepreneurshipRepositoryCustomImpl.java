package com.emcove.rest.api.Core.repository;

import com.emcove.rest.api.Core.response.Category;
import com.emcove.rest.api.Core.response.Entrepreneurship;
import com.emcove.rest.api.Core.response.Order;
import com.emcove.rest.api.Core.response.OrderState;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Repository
public class EntrepreneurshipRepositoryCustomImpl implements EntrepreneurshipRepositoryCustom{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Entrepreneurship> find(Set<Category> categories, String name, String productName) {
        StringBuilder sb = new StringBuilder();
        boolean filterByCategory = categories != null && !categories.isEmpty();
        boolean filterByName = name != null && !name.equals("");
        boolean filterByProductName = productName != null && !productName.equals("");
        sb.append("SELECT DISTINCT ent from Entrepreneurship as ent ");
        sb.append("INNER JOIN  ent.categories as cat ");
        sb.append("LEFT JOIN  ent.products as product ");
        sb.append("WHERE ");

        if(filterByName && filterByProductName) {
            sb.append("( LOWER(ent.name) like :name ");
            sb.append(" OR LOWER(product.name) like :productName )");
        }
        if(filterByCategory)
            sb.append((filterByName && filterByProductName? " AND" : "" )+ " cat in (:categories) ");

        sb.append("ORDER BY ent.hasSubscription DESC");

        Query query = entityManager.createQuery(sb.toString());

        if(filterByCategory)
            query.setParameter("categories", categories);
        if(filterByName && filterByProductName) {
            query.setParameter("name", "%" + URLDecoder.decode(name, StandardCharsets.UTF_8).toLowerCase() + "%");
            query.setParameter("productName", "%" + URLDecoder.decode(productName, StandardCharsets.UTF_8).toLowerCase() + "%");
        }

        try {
            return (List<Entrepreneurship>)query.getResultList();
        }catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }


    }

    @Override
    public List<Order> findOrdersByEntrepreneurship(Integer entrepreneurshipId) {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT DISTINCT ordr from Order as ordr ");
        sb.append("INNER JOIN ordr.entrepreneurship as ent ");
        sb.append("WHERE ent.id = :entrepreneurshipId");

        Query query = entityManager.createQuery(sb.toString());
        query.setParameter("entrepreneurshipId",entrepreneurshipId);

        try {
            return (List<Order>)query.getResultList();
        }catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public List<Entrepreneurship> findBySubscriptionExpirationDate(Date currentTime) {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT DISTINCT ent from Entrepreneurship as ent ");
        sb.append("WHERE ent.hasSubscription = '1' ");
        sb.append("AND ent.subscriptionExpirationDate <= :currentTime");

        Query query = entityManager.createQuery(sb.toString());
        query.setParameter("currentTime",currentTime);

        try {
            return (List<Entrepreneurship>)query.getResultList();
        }catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public List<Order> findOrdersByEntrepreneurshipFilter(Integer entrepreneurshipId, OrderState orderState) {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT DISTINCT ordr from Order as ordr ");
        sb.append("INNER JOIN ordr.entrepreneurship as ent ");
        sb.append("WHERE ent.id = :entrepreneurshipId ");
        if(orderState != null)
            sb.append("AND ordr.currentState = :orderState ");



        Query query = entityManager.createQuery(sb.toString());
        query.setParameter("entrepreneurshipId",entrepreneurshipId);
        if(orderState != null)
            query.setParameter("orderState",orderState);

        try {
            return (List<Order>)query.getResultList();
        }catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
