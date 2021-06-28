package com.emcove.rest.api.Core.serviceimp;

import com.emcove.rest.api.Core.dto.EntrepreneurshipDTO;
import com.emcove.rest.api.Core.repository.EntrepreneurshipRepository;
import com.emcove.rest.api.Core.repository.UserRepository;
import com.emcove.rest.api.Core.response.Comment;
import com.emcove.rest.api.Core.response.Entrepreneurship;
import com.emcove.rest.api.Core.response.Product;
import com.emcove.rest.api.Core.response.Reputation;
import com.emcove.rest.api.Core.response.User;
import com.emcove.rest.api.Core.service.EntrepreneurshipService;
import com.emcove.rest.api.Core.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntrepreneurshipServiceImpl implements EntrepreneurshipService {
    @Autowired
    private EntrepreneurshipRepository entrepreneurshipRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private ProductService productService;

    @Override
    public Entrepreneurship createEntrepreneurship(Entrepreneurship entrepreneurship) {
        return entrepreneurshipRepository.save(entrepreneurship);
    }

    @Override
    public void deleteEntrepreneurship(Integer id) {
        try {
            Optional<User> user = userRepository.findByEntrepreneurshipId(id);
            if(user.isPresent())
                user.get().setEntrepreneurship(null);
            entrepreneurshipRepository.deleteById(id);
        }catch (Exception e){
            e.printStackTrace();
        }

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
    public Entrepreneurship addProduct(Entrepreneurship entrepreneurship, Product product) throws Exception {
        entrepreneurship.addProduct(product);
        return entrepreneurshipRepository.save(entrepreneurship);
    }

    @Override
    public Entrepreneurship patchEntrepreneurship(Integer id, EntrepreneurshipDTO entrepreneurshipDTO) {
        Optional<Entrepreneurship> entrepreneurshipOpt = entrepreneurshipRepository.findById(id);
        if(!entrepreneurshipOpt.isPresent())
            return null;
        Entrepreneurship entrepreneurship = entrepreneurshipOpt.get();

        if(entrepreneurshipDTO.getName() != null)
            entrepreneurship.setName(entrepreneurshipDTO.getName());
        if(entrepreneurshipDTO.getLogo() != null)
            entrepreneurship.setLogo(entrepreneurshipDTO.getLogo());
        if(entrepreneurshipDTO.getCity() != null)
            entrepreneurship.setCity(entrepreneurshipDTO.getCity());
        if(entrepreneurshipDTO.getDoesShipments() != null)
            entrepreneurship.setDoesShipments(entrepreneurshipDTO.getDoesShipments());
        if (entrepreneurshipDTO.getCategories() != null && !entrepreneurshipDTO.getCategories().isEmpty())
            entrepreneurship.setCategories(entrepreneurshipDTO.getCategories());

        return entrepreneurshipRepository.save(entrepreneurship);
    }

    @Override
    public List<Entrepreneurship> findAll() {
        return entrepreneurshipRepository.findAll();
    }

    @Override
    public Reputation addComment(Integer id, Comment comment) throws Exception {
        Optional<Entrepreneurship> entrepreneurshipOpt = entrepreneurshipRepository.findById(id);
        if(entrepreneurshipOpt.isPresent()){
            entrepreneurshipOpt.get().getReputation().addComent(comment);
            return entrepreneurshipRepository.save(entrepreneurshipOpt.get()).getReputation();
        }else
            throw new Exception("No se encontro ningún usuario");
    }

    @Override
    public Reputation getReputation(Integer id) throws Exception {
        Optional<Entrepreneurship> entrepreneurshipOpt = entrepreneurshipRepository.findById(id);
        if(entrepreneurshipOpt.isPresent()){
            return entrepreneurshipOpt.get().getReputation();
        }else
            throw new Exception("No se encontro ningún usuario");
    }
}
