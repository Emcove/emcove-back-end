package com.emcove.rest.api.Core.service;

import com.emcove.rest.api.Core.dto.EntrepreneurshipDTO;
import com.emcove.rest.api.Core.dto.SubscriptionPlanDTO;
import com.emcove.rest.api.Core.response.Category;
import com.emcove.rest.api.Core.response.Comment;
import com.emcove.rest.api.Core.response.Entrepreneurship;
import com.emcove.rest.api.Core.response.Product;
import com.emcove.rest.api.Core.response.Reputation;


import java.util.List;
import java.util.Set;

public interface EntrepreneurshipService {
    Entrepreneurship createEntrepreneurship(Entrepreneurship entrepreneurship);
    void deleteEntrepreneurship(Integer id);
    Entrepreneurship updateEntrepreneurship(Entrepreneurship entrepreneurship);

    Entrepreneurship findEntrepreneurshipById(Integer id);

    Entrepreneurship addProduct(Integer entrepreneurshipId, Product product);

    Entrepreneurship patchEntrepreneurship(Integer id, EntrepreneurshipDTO entrepreneurshipDTO);

    List<Entrepreneurship> findAll(Set<Category> categories, String name, String productName);

    Reputation addComment(Integer id, Comment comment);

    Reputation getReputation(Integer id);

    Reputation getReputationByUsername(String loggedUsername);

    void subscribe(Integer id, SubscriptionPlanDTO plan);
}
