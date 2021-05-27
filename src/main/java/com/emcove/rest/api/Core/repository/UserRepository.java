package com.emcove.rest.api.Core.repository;

import com.emcove.rest.api.Core.response.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
