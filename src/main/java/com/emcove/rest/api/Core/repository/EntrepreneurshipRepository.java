package com.emcove.rest.api.Core.repository;

import com.emcove.rest.api.Core.response.Entrepreneurship;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface EntrepreneurshipRepository extends JpaRepository<Entrepreneurship,Integer> {
    Optional<Entrepreneurship> findByName(String name);
}
