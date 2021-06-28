package com.emcove.rest.api.Core.serviceimp;

import com.emcove.rest.api.Core.dto.UserDTO;
import com.emcove.rest.api.Core.exception.ResourceNotFoundException;
import com.emcove.rest.api.Core.repository.UserRepository;

import com.emcove.rest.api.Core.response.Entrepreneurship;
import com.emcove.rest.api.Core.response.Comment;
import com.emcove.rest.api.Core.response.Reputation;
import com.emcove.rest.api.Core.response.User;
import com.emcove.rest.api.Core.service.EntrepreneurshipService;
import com.emcove.rest.api.Core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.EntityExistsException;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private EntrepreneurshipService entrepreneurshipService;

    @Override
    public void createUser(User newUser){
        Optional<User> user = userRepository.findByUsername(newUser.getUsername());

        if(!user.isPresent()){
            try {
                newUser.setReputation(new Reputation());
                newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
                userRepository.save(newUser);
            }catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("No se ha podido crear el usuario, intente nuevamente mas tarde",e);
            }
        }else{
            throw new EntityExistsException("El nombre de usuario ya existe");
        }

    }

    @Override
    public void deleteUser(Integer id) {

    }

    @Override
    public User patchUser(UserDTO userDTO) {
        Optional<User> userOpt = userRepository.findByUsername(getLoggedUsername());

        if(!userOpt.isPresent())
            return null;
        User user = userOpt.get();

        if(userDTO.getAvatar() != null)
            user.setAvatar(userDTO.getAvatar());
        if(userDTO.getName() != null)
            user.setName(userDTO.getName());
        if(userDTO.getSurname() != null)
            user.setSurname(userDTO.getSurname());
        if(userDTO.getCity() != null)
            user.setCity(userDTO.getCity());
        if(userDTO.getAdult() != null)
            user.setAdult(userDTO.getAdult());
        if(userDTO.getEmail() != null)
            user.setEmail(userDTO.getEmail());
        if(userDTO.getPassword() != null)
            user.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));


        return userRepository.save(user);
    }

    @Override
    public String getLoggedUsername(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;

        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        return username;
    }

    @Override
    public Optional<User> findUserById(Integer id) {
        return userRepository.findById(id);
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

    @Override
    public User createEntrepreneurship(String username, Entrepreneurship entrepreneurship)  {
        Optional<User> userOpt = userRepository.findByUsername(username);

        if(userOpt.isPresent()){
            User user = userOpt.get();
            if(user.getEntrepreneurship() != null)
                throw new EntityExistsException("Ya cuenta con un emprendimiento creado");

            user.setEntrepreneurship(entrepreneurship);
            return userRepository.save(user);
        }else
            throw new ResourceNotFoundException("No se encontro ningún usuario");

    }

    @Override
    public Reputation addComment(Integer id, Comment comment) {
        Optional<User> userOpt = userRepository.findById(id);
        if(userOpt.isPresent()){
            userOpt.get().getReputation().addComent(comment);
            return userRepository.save(userOpt.get()).getReputation();
        }else
            throw new ResourceNotFoundException("No se encontro ningún usuario");

    }

    @Override
    public Reputation getReputation(Integer id) {
        Optional<User> userOpt = userRepository.findById(id);
        if(!userOpt.isPresent())
            throw new ResourceNotFoundException("No se encontro ningún usuario");

        return userOpt.get().getReputation();
    }

    @Override
    public Reputation getReputationByUsername(String username) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if(!userOpt.isPresent())
            throw new ResourceNotFoundException("No se encontro ningún usuario");

        return userOpt.get().getReputation();
    }
}
