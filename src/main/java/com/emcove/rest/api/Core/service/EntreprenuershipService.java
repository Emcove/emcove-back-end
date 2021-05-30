package com.emcove.rest.api.Core.service;

import com.emcove.rest.api.Core.response.Entreprenuership;


import java.util.Optional;

public interface EntreprenuershipService {
    Entreprenuership createEntreprenuership(Entreprenuership entreprenuership);
    void deleteEntreprenuership(Integer id);
    Entreprenuership updateEntreprenuership(Entreprenuership entreprenuership);

    Optional<Entreprenuership> findEntreprenuershipById(Integer id);
}
