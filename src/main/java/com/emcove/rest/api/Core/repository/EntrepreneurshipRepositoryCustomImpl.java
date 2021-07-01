package com.emcove.rest.api.Core.repository;

import com.emcove.rest.api.Core.response.Category;
import com.emcove.rest.api.Core.response.Entrepreneurship;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
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
        sb.append("INNER JOIN  ent.products as product ");
        sb.append("WHERE ");
        if(filterByCategory)
            sb.append("cat in (:categories) ");
        if(filterByName)
            sb.append((filterByCategory? " OR" : "") + " LOWER(ent.name) like :name ");
        if(filterByProductName)
            sb.append((filterByCategory || filterByName? " OR" : "" )+" LOWER(product.name) like :productName ");

        Query query = entityManager.createQuery(sb.toString());

        if(filterByCategory)
            query.setParameter("categories", categories);
        if(filterByName)
            query.setParameter("name", "%" + URLDecoder.decode(name, StandardCharsets.UTF_8).toLowerCase() + "%");
        if(filterByProductName)
            query.setParameter("productName", "%" + URLDecoder.decode(productName, StandardCharsets.UTF_8).toLowerCase() + "%");
        try {
            return query.getResultList();
        }catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
       /* CriteriaQuery<Entrepreneurship> query = cb.createQuery(Entrepreneurship.class);
        Root<Entrepreneurship> entrepreneurship = query.from(Entrepreneurship.class);
        Join<Entrepreneurship, Product> product = entrepreneurship.join("products");
        Expression<String> categoriesPath = entrepreneurship.get("categories");
        Path<String> namePath = entrepreneurship.get("name");
        Path<String> productPath = product.get("name");
        List<Predicate> predicates = new ArrayList<>();


        if(categories != null && !categories.isEmpty())
            predicates.add(categoriesPath.in(categories));
        if(name != null && !name.equals(""))
            predicates.add(cb.like(namePath,name));
        if(productName != null && !productName.equals(""))
            predicates.add(cb.like(productPath,productName));
        query.select(entrepreneurship)
                .where(cb.or(predicates.toArray(new Predicate[predicates.size()])));
        System.out.println(entityManager.createQuery(query).toString());
        try {
            return entityManager.createQuery(query)
                    .getResultList();
        }catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }*/

    }
}
