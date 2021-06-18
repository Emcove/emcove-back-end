package com.emcove.rest.api.Core.service;

import com.emcove.rest.api.Core.dto.EntrepreneurshipDTO;
import com.emcove.rest.api.Core.response.Comment;
import com.emcove.rest.api.Core.response.Entrepreneurship;
import com.emcove.rest.api.Core.response.Product;
import com.emcove.rest.api.Core.response.Reputation;


import java.util.List;
import java.util.Optional;

public interface EntrepreneurshipService {
    Entrepreneurship createEntrepreneurship(Entrepreneurship entrepreneurship);
    void deleteEntrepreneurship(Integer id);
    Entrepreneurship updateEntrepreneurship(Entrepreneurship entrepreneurship);

    Optional<Entrepreneurship> findEntrepreneurshipById(Integer id);

    Entrepreneurship addProduct(Entrepreneurship entrepreneurship, Product product) throws Exception;

    Entrepreneurship patchEntrepreneurship(Integer id, EntrepreneurshipDTO entrepreneurshipDTO);

    List<Entrepreneurship> findAll();

    Reputation addComment(Integer id, Comment comment) throws Exception;

    Reputation getReputation(Integer id) throws Exception;

    void validateEntrepreneurship(Entrepreneurship entrepreneurship)throws Exception;
}
