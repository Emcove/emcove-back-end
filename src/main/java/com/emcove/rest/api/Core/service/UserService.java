package com.emcove.rest.api.Core.service;

import com.emcove.rest.api.Core.response.User;

public interface UserService {
    void createUser(User newUser);
    void deleteUser(Integer id);
    void updateUser(User user);
}
