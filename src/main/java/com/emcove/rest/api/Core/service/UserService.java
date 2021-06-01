package com.emcove.rest.api.Core.service;

import com.emcove.rest.api.Core.response.User;

import java.util.Optional;

public interface UserService {
    void createUser(User newUser);
    void deleteUser(Integer id);
    void updateUser(User user);
    Optional<User> findUserById(Integer id);
    boolean checkUserPassword(User user);
}
