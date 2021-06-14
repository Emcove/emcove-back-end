package com.emcove.rest.api.Core.service;

import com.emcove.rest.api.Core.response.Comment;
import com.emcove.rest.api.Core.response.Entrepreneurship;
import com.emcove.rest.api.Core.response.Reputation;
import com.emcove.rest.api.Core.dto.UserDTO;
import com.emcove.rest.api.Core.response.User;

import java.util.Optional;

public interface UserService {
    void createUser(User newUser) throws Exception;
    void deleteUser(Integer id);
    User patchUser(UserDTO userDTO);
    Optional<User> findUserById(Integer id);
    String getLoggedUsername();
    boolean checkUserPassword(User user);
    User createEntrepreneurship(String username, Entrepreneurship entrepreneurship) throws Exception;
    Reputation addComment(Integer id, Comment comment) throws Exception;
    Reputation getReputation(Integer id) throws Exception;
}
