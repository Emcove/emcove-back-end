package com.emcove.rest.api.Core.serviceimp;

import com.emcove.rest.api.Core.dto.EntrepreneurshipDTO;
import com.emcove.rest.api.Core.exception.ResourceNotFoundException;
import com.emcove.rest.api.Core.repository.EntrepreneurshipRepository;
import com.emcove.rest.api.Core.repository.EntrepreneurshipRepositoryCustom;
import com.emcove.rest.api.Core.repository.UserRepository;
import com.emcove.rest.api.Core.response.Category;
import com.emcove.rest.api.Core.response.Comment;
import com.emcove.rest.api.Core.response.Entrepreneurship;
import com.emcove.rest.api.Core.response.Product;
import com.emcove.rest.api.Core.response.Reputation;
import com.emcove.rest.api.Core.response.User;
import com.emcove.rest.api.Core.service.EntrepreneurshipService;
import com.emcove.rest.api.Core.service.ProductService;
import org.springframework.beans.InvalidPropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class EntrepreneurshipServiceImpl implements EntrepreneurshipService {
    @Autowired
    private EntrepreneurshipRepository entrepreneurshipRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntrepreneurshipRepositoryCustom entrepreneurshipRepositoryCustom;

    @Autowired
    private ProductService productService;

    @Override
    public Entrepreneurship createEntrepreneurship(Entrepreneurship entrepreneurship) {
        return entrepreneurshipRepository.save(entrepreneurship);
    }

    @Override
    public void deleteEntrepreneurship(Integer id) {
        Optional<User> user = userRepository.findByEntrepreneurshipId(id);
        if(user.isPresent())
            user.get().setEntrepreneurship(null);
        entrepreneurshipRepository.deleteById(id);
    }

    @Override
    public Entrepreneurship updateEntrepreneurship(Entrepreneurship entrepreneurship) {
        if(entrepreneurship.getId() == null)
            throw new IllegalArgumentException("No se permite nuevo emprendimiento");

        return entrepreneurshipRepository.save(entrepreneurship);
    }

    @Override
    public Entrepreneurship findEntrepreneurshipById(Integer id) {
        Optional<Entrepreneurship> entrepreneurshipOpt = entrepreneurshipRepository.findById(id);
        if(entrepreneurshipOpt.isEmpty())
            throw new ResourceNotFoundException("No se encontro ningún emprendimiento");

        return entrepreneurshipOpt.get();
    }

    @Override
    public Entrepreneurship findEntrepreneurshipByName(String name) {
        Optional<Entrepreneurship> entrepreneurshipOpt = entrepreneurshipRepository.findByName(name);
        if(entrepreneurshipOpt.isEmpty())
            throw new ResourceNotFoundException("No se encontro ningún emprendimiento");

        return entrepreneurshipOpt.get();
    }

    @Override
    public Entrepreneurship addProduct(Integer entrepreneurshipId, Product product) {
        Optional<Entrepreneurship> entrepreneurshipOp = entrepreneurshipRepository.findById(entrepreneurshipId);
        if(entrepreneurshipOp.isEmpty())
            throw new ResourceNotFoundException("No se encontro ningún emprendimiento");

        Entrepreneurship entrepreneurship = entrepreneurshipOp.get();
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
    public List<Entrepreneurship> findAll(Set<Category> categories, String name, String productName) {
        if((categories != null && !categories.isEmpty()) || (name != null && !name.equals("")) || (productName != null && !productName.equals("")))
            return entrepreneurshipRepositoryCustom.find(categories,name,productName);
        else
            return entrepreneurshipRepository.findAll();
    }

    @Override
    public Reputation addComment(Integer id, Comment comment){
        Optional<Entrepreneurship> entrepreneurshipOpt = entrepreneurshipRepository.findById(id);
        if(entrepreneurshipOpt.isPresent()){
            entrepreneurshipOpt.get().getReputation().addComent(comment);
            return entrepreneurshipRepository.save(entrepreneurshipOpt.get()).getReputation();
        }else
            throw new ResourceNotFoundException("No se encontro ningún usuario");
    }

    @Override
    public Reputation getReputation(Integer id) {
        Optional<Entrepreneurship> entrepreneurshipOpt = entrepreneurshipRepository.findById(id);
        if(entrepreneurshipOpt.isEmpty())
            throw new ResourceNotFoundException("No se encontro ningún emprendimiento");

        return entrepreneurshipOpt.get().getReputation();
    }

    @Override
    public Reputation getReputationByUsername(String loggedUsername) {
        Optional<User> user = userRepository.findByUsername(loggedUsername);

        if(user.isEmpty())
            throw new ResourceNotFoundException("No se encontro ningún usuario");
        if(!user.get().hasEntrepreneuship())
            throw new ResourceNotFoundException("No se encontro ningún emprendimiento");

        return user.get().getEntrepreneurship().getReputation();
    }

    @Override
    public Entrepreneurship getEntrepreneurshipByUsername(String loggedUsername) {
        Optional<User> user = userRepository.findByUsername(loggedUsername);

        if(user.isEmpty())
            throw new ResourceNotFoundException("No se encontro ningún usuario");
        if(!user.get().hasEntrepreneuship())
            throw new ResourceNotFoundException("No se encontro ningún emprendimiento");

        return user.get().getEntrepreneurship();
    }
}
