package com.emcove.rest.api.Core.service;

import com.emcove.rest.api.Core.response.Comment;
import com.emcove.rest.api.Core.response.Entrepreneurship;
import com.emcove.rest.api.Core.response.Order;
import com.emcove.rest.api.Core.response.Reputation;
import com.emcove.rest.api.Core.dto.UserDTO;
import com.emcove.rest.api.Core.response.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void createUser(User newUser);
    void deleteUser(String username);

    User patchUser(UserDTO userDTO);
    User findUserById(Integer id);
    String getLoggedUsername();
    boolean checkUserPassword(User user);
    User createEntrepreneurship(String username, Entrepreneurship entrepreneurship);
    Reputation addComment(Integer id, Comment comment);
    Reputation getReputation(Integer id);
    Reputation getReputationByUsername(String username);

    User getLoggedUser();

    List<Order> getOrders(String username);

}
