package com.emcove.rest.api.Core.service;

import com.emcove.rest.api.Core.dto.EntrepreneurshipDTO;
import com.emcove.rest.api.Core.dto.UserDTO;
import com.emcove.rest.api.Core.response.Entrepreneurship;
import com.emcove.rest.api.Core.response.User;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Optional;

public interface UserService {
    void createUser(User newUser) throws Exception;
    void deleteUser(Integer id);
    User patchUser(UserDTO userDTO);
    Optional<User> findUserById(Integer id);
    String getLoggedUsername();
    boolean checkUserPassword(User user);
}
