package com.rest.service;

import com.rest.model.User;
import com.rest.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User createUser(User user){
        return userRepository.save(user);
    }

    public Optional<User> findUserById(UUID id){
        return userRepository.findById(id);
    }

    public User updateUser(UUID id, User user){
        user.setId(id);
        return userRepository.save(user);
    }

    public void deleteUser(UUID id){
        if (userRepository.findById(id).isPresent()){
            userRepository.deleteById(id);
        }
    }

    public User findByUsername(String username){
        return userRepository.findUserByUsername(username);
    }
}
