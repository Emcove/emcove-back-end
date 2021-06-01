package com.emcove.rest.api.Core.repository;

import com.emcove.rest.api.Core.response.Entreprenuership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

public interface EntreprenuershipRepository extends JpaRepository<Entreprenuership,Integer> {
}
