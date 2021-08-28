package com.emcove.rest.api.Core.repository;


import com.emcove.rest.api.Core.response.Entrepreneurship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Transactional
public interface EntrepreneurshipRepository extends JpaRepository<Entrepreneurship,Integer> {
    Optional<Entrepreneurship> findByName(String name);
    List<Entrepreneurship> findAllByOrderByHasSubscriptionDesc();
}
