package com.emcove.rest.api.Core.serviceimp;

import com.emcove.rest.api.Core.dto.EntrepreneurshipDTO;
import com.emcove.rest.api.Core.repository.EntrepreneurshipRepository;
import com.emcove.rest.api.Core.response.Entrepreneurship;
import com.emcove.rest.api.Core.response.Product;
import com.emcove.rest.api.Core.response.Reputation;
import com.emcove.rest.api.Core.service.EntrepreneurshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntrepreneurshipServiceImpl implements EntrepreneurshipService {
    @Autowired
    private EntrepreneurshipRepository entrepreneurshipRepository;

    @Override
    public Entrepreneurship createEntrepreneurship(Entrepreneurship entrepreneurship) {
        entrepreneurship.setReputation(new Reputation());
        return entrepreneurshipRepository.save(entrepreneurship);
    }

    @Override
    public void deleteEntrepreneurship(Integer id) {
        entrepreneurshipRepository.deleteById(id);
    }

    @Override
    public Entrepreneurship updateEntrepreneurship(Entrepreneurship entrepreneurship) {
        return entrepreneurshipRepository.save(entrepreneurship);
    }

    @Override
    public Optional<Entrepreneurship> findEntrepreneurshipById(Integer id) {
        return entrepreneurshipRepository.findById(id);
    }

    @Override
    public Entrepreneurship addProduct(Entrepreneurship entrepreneurship, Product product) {
        entrepreneurship.addProduct(product);
        return entrepreneurshipRepository.save(entrepreneurship);
    }

    @Override
    public Entrepreneurship patchEntrepreneurship(Integer id, EntrepreneurshipDTO entrepreneurshipDTO) {
        Optional<Entrepreneurship> entrepreneurshipOpt = entrepreneurshipRepository.findById(id);
        if(!entrepreneurshipOpt.isPresent())
            return null;
        Entrepreneurship entrepreneurship = entrepreneurshipOpt.get();

        if(entrepreneurshipDTO.getDescription() != null)
            entrepreneurship.setDescription(entrepreneurshipDTO.getDescription());
        if(entrepreneurshipDTO.getName() != null)
            entrepreneurship.setName(entrepreneurshipDTO.getName());
        if (entrepreneurshipDTO.getCategories() != null && !entrepreneurshipDTO.getCategories().isEmpty())
            entrepreneurship.setCategories(entrepreneurshipDTO.getCategories());

        return entrepreneurshipRepository.save(entrepreneurship);
    }

    @Override
    public List<Entrepreneurship> findAll() {
        return entrepreneurshipRepository.findAll();
    }
}