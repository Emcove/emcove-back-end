package com.emcove.rest.api.Core.serviceimp;

import com.emcove.rest.api.Core.dto.EntrepreneurshipDTO;
import com.emcove.rest.api.Core.repository.EntreprenuershipRepository;
import com.emcove.rest.api.Core.response.Entreprenuership;
import com.emcove.rest.api.Core.response.Product;
import com.emcove.rest.api.Core.response.Reputation;
import com.emcove.rest.api.Core.service.EntreprenuershipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EntreprenuershipServiceImpl implements EntreprenuershipService {
    @Autowired
    private EntreprenuershipRepository entreprenuershipRepository;

    @Override
    public Entreprenuership createEntreprenuership(Entreprenuership entreprenuership) {
        entreprenuership.setReputation(new Reputation());
        return entreprenuershipRepository.save(entreprenuership);
    }

    @Override
    public void deleteEntreprenuership(Integer id) {
        entreprenuershipRepository.deleteById(id);
    }

    @Override
    public Entreprenuership updateEntreprenuership(Entreprenuership entreprenuership) {
        return entreprenuershipRepository.save(entreprenuership);
    }

    @Override
    public Optional<Entreprenuership> findEntreprenuershipById(Integer id) {
        return entreprenuershipRepository.findById(id);
    }

    @Override
    public Entreprenuership addProduct(Entreprenuership entreprenuership, Product product) {
        entreprenuership.addProduct(product);
        return entreprenuershipRepository.save(entreprenuership);
    }

    @Override
    public Entreprenuership patchEntrepreneurship(Integer id, EntrepreneurshipDTO entrepreneurshipDTO) {
        Optional<Entreprenuership> entrepreneurshipOpt = entreprenuershipRepository.findById(id);
        if(!entrepreneurshipOpt.isPresent())
            return null;
        Entreprenuership entrepreneurship = entrepreneurshipOpt.get();

        if(entrepreneurshipDTO.getDescription() != null)
            entrepreneurship.setDescription(entrepreneurshipDTO.getDescription());
        if(entrepreneurshipDTO.getName() != null)
            entrepreneurship.setName(entrepreneurshipDTO.getName());
        if (entrepreneurshipDTO.getCategories() != null && !entrepreneurshipDTO.getCategories().isEmpty())
            entrepreneurship.setCategories(entrepreneurshipDTO.getCategories());

        return entrepreneurship;
    }
}
