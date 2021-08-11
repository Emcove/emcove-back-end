package com.emcove.rest.api.Core.serviceimp;

import com.emcove.rest.api.Core.dto.EntrepreneurshipDTO;
import com.emcove.rest.api.Core.dto.SubscriptionPlanDTO;
import com.emcove.rest.api.Core.exception.ResourceNotFoundException;
import com.emcove.rest.api.Core.repository.EntrepreneurshipRepository;
import com.emcove.rest.api.Core.repository.EntrepreneurshipRepositoryCustom;
import com.emcove.rest.api.Core.repository.OrderRepository;
import com.emcove.rest.api.Core.repository.UserRepository;
import com.emcove.rest.api.Core.response.Category;
import com.emcove.rest.api.Core.response.Comment;
import com.emcove.rest.api.Core.response.Entrepreneurship;
import com.emcove.rest.api.Core.response.Order;
import com.emcove.rest.api.Core.response.OrderState;
import com.emcove.rest.api.Core.response.OrderTrackingData;
import com.emcove.rest.api.Core.response.Product;
import com.emcove.rest.api.Core.response.Reputation;
import com.emcove.rest.api.Core.response.User;
import com.emcove.rest.api.Core.service.EntrepreneurshipService;
import com.emcove.rest.api.Core.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
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
    private OrderRepository orderRepository;


    @Override
    public Entrepreneurship createEntrepreneurship(Entrepreneurship entrepreneurship) {
        return entrepreneurshipRepository.save(entrepreneurship);
    }

    @Override
    public void deleteEntrepreneurship(Integer id) {
        Optional<User> user = userRepository.findByEntrepreneurshipId(id);

        user.ifPresent(value -> value.setEntrepreneurship(null));

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
        if(entrepreneurshipOpt.isEmpty())
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
            return entrepreneurshipRepository.findAllByOrderByHasSubscriptionDesc();
    }

    @Override
    public Reputation addComment(Integer id, Comment comment){
        Optional<Entrepreneurship> entrepreneurshipOpt = entrepreneurshipRepository.findById(id);
        if(entrepreneurshipOpt.isPresent()){
            entrepreneurshipOpt.get().getReputation().addComent(comment);
            return entrepreneurshipRepository.save(entrepreneurshipOpt.get()).getReputation();
        }else
            throw new ResourceNotFoundException("No se encontro ningún emprendimiento");
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

    public Order addOrder(Integer id, Order order, String loggedUsername) {
        Optional<User> user = userRepository.findByUsername(loggedUsername);
        Optional<Entrepreneurship> entrepreneurshipOpt = entrepreneurshipRepository.findById(id);
        if (entrepreneurshipOpt.isEmpty())
            throw new ResourceNotFoundException("No se encontro ningún emprendimiento");
        if (user.isEmpty())
            throw new ResourceNotFoundException("No se encontro ningún usuario");

        order.setUser(user.get());
        order.setEntrepreneurship(entrepreneurshipOpt.get());
        order.addTrackingData(new OrderTrackingData(OrderState.PENDIENTE));

        orderRepository.save(order);

        return order;
    }

    @Override
    public List<Order> getOrders(String loggedUsername) {
        Optional<User> user = userRepository.findByUsername(loggedUsername);
        if(user.isEmpty())
            throw new ResourceNotFoundException("No se encontro ningún usuario");
        if(!user.get().hasEntrepreneuship())
            throw new ResourceNotFoundException("No se encontro ningún emprendimiento");

        return entrepreneurshipRepositoryCustom.findOrdersByEntrepreneuship(user.get().getEntrepreneurship().getId());
    }

    @Override
    public Order addOrderTrackingToOrder(Integer orderId, OrderState newOrderState, String loggedUsername) throws IllegalAccessException {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        Optional<User> user = userRepository.findByUsername(loggedUsername);

        if (optionalOrder.isEmpty())
            throw new ResourceNotFoundException("No se encontro ninguna orden");
        if (user.isEmpty())
            throw new ResourceNotFoundException("No se encontro ningún usuario");
        if (!user.get().hasEntrepreneuship())
            throw new ResourceNotFoundException("No se encontro ningún emprendimiento");
        Order order = optionalOrder.get();

        if (!order.getEntrepreneurship().getId().equals(user.get().getEntrepreneurship().getId()))
            throw new IllegalAccessException("El pedido deseado no pertenece al emprendimiento logueado");

        if (order.isClosed())
            throw new IllegalArgumentException("El pedido se encuentra cerrado");

        order.addTrackingData(new OrderTrackingData(newOrderState));

        if (newOrderState.equals(OrderState.ENTREGADO))
            order.setDeliverDate(LocalDate.now());

        orderRepository.save(order);

        return order;
    }

    @Override
    public void subscribe(Integer id, SubscriptionPlanDTO plan) {
        Optional<Entrepreneurship> entrepreneurshipOpt = entrepreneurshipRepository.findById(id);
        if(entrepreneurshipOpt.isEmpty())
            throw new ResourceNotFoundException("No se encontro ningún emprendimiento");

        entrepreneurshipOpt.get().subscribe(plan);
        entrepreneurshipRepository.save(entrepreneurshipOpt.get());

    }

    @Override
    public void checkExpiredSubscriptions(Date currentTime) {
        List<Entrepreneurship> entrepreneurshipsToExpired = entrepreneurshipRepositoryCustom.findBySubscriptionExpirationDate(currentTime);

        for(Entrepreneurship ent : entrepreneurshipsToExpired){
            ent.setHasSubscription("0");
            entrepreneurshipRepository.save(ent);
        }
    }
}
