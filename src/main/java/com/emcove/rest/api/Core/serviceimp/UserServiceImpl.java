package com.emcove.rest.api.Core.serviceimp;

import com.emcove.rest.api.Core.repository.UserRepository;
import com.emcove.rest.api.Core.response.User;
import com.emcove.rest.api.Core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public void createUser(User newUser) {

    }

    @Override
    public void deleteUser(Integer id) {

    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public Optional<User> findUserById(Integer id) {
        Optional<User> user = userRepository.findById(id);

        return user;
    }
}
