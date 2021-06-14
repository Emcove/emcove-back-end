package com.emcove.rest.api.Core.repository;

import com.emcove.rest.api.Core.response.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
public interface UserRepository extends JpaRepository<User, Integer> {
    Boolean existsByUsername(String username);
    Optional<User> findByUsername(String username);
    Optional<User> findByEntrepreneurshipId(Integer id);
}
