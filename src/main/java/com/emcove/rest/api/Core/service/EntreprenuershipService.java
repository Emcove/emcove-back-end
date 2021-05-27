package com.emcove.rest.api.Core.service;

import com.emcove.rest.api.Core.response.Entreprenuership;

public interface EntreprenuershipService {
    void createEntreprenuership(Entreprenuership entreprenuership);
    void deleteEntreprenuership(Integer id);
    void updateEntreprenuership(Entreprenuership entreprenuership);
}
