package com.emcove.rest.api.Core.service;

import com.emcove.rest.api.Core.response.Entrepreneurship;
import com.emcove.rest.api.Core.response.User;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Optional;

public interface UserService {
    void createUser(User newUser) throws Exception;
    void deleteUser(Integer id);
    void updateUser(User user);
    Optional<User> findUserById(Integer id);
    boolean checkUserPassword(User user);

    User createEntrepreneurship(Integer userId, Entrepreneurship entrepreneurship) throws Exception;
}
