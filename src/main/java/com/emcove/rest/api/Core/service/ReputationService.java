package com.emcove.rest.api.Core.service;

import com.emcove.rest.api.Core.response.Reputation;

public interface ReputationService {
    Reputation createReputation();
    void updateReputation(Reputation reputation);
}
