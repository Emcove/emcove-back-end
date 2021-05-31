package com.emcove.rest.api.Core.serviceimp;

import com.emcove.rest.api.Core.repository.UserRepository;
import com.emcove.rest.api.Core.response.Reputation;
import com.emcove.rest.api.Core.response.User;
import com.emcove.rest.api.Core.service.ReputationService;
import com.emcove.rest.api.Core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void createUser(User newUser) {
        try {
            newUser.setReputation(new Reputation());
            newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
            userRepository.save(newUser);
        }catch (Exception e) {
            System.out.println("error");
        }
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

    @Override
    public boolean checkUserPassword(User user) {
        Optional<User> userOpt = userRepository.findByUsername(user.getUsername());
        if(userOpt.isPresent()){
            if(BCrypt.checkpw(user.getPassword(),userOpt.get().getPassword())){
                return true;
            }
        }
        return false;
    }
}
