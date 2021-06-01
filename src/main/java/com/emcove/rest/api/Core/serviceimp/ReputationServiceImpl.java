package com.emcove.rest.api.Core.serviceimp;

import com.emcove.rest.api.Core.repository.ReputationRepository;
import com.emcove.rest.api.Core.response.Reputation;
import com.emcove.rest.api.Core.service.ReputationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReputationServiceImpl implements ReputationService {
    @Autowired
    private ReputationRepository reputationRepository;

    @Override
    public Reputation createReputation() {
        return reputationRepository.save(new Reputation());
    }

    @Override
    public void updateReputation(Reputation reputation) {

    }
}
