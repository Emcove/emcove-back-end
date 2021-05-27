package com.emcove.rest.api.Core.repository;

import com.emcove.rest.api.Core.response.Reputation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReputationRepository extends JpaRepository<Reputation, Integer> {
}
