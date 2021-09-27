package com.emcove.rest.api.Core.repository;

import com.emcove.rest.api.Core.response.DeliveryPoint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DeliveryPointRepository extends JpaRepository<DeliveryPoint,Integer> {
    Optional<DeliveryPoint>  findById(Integer id);
}
