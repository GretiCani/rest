package com.rest.service;

import com.rest.exception.UserExistException;
import com.rest.exception.UserNotFoundException;
import com.rest.model.User;
import com.rest.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User createUser(User user)throws UserExistException {

        if (findByUsername(user.getUsername())!=null)
            throw new UserExistException("User already exist.");
        return userRepository.save(user);
    }

    public User findUserById(UUID id) throws UserNotFoundException {
        return userRepository.findById(id).orElseThrow(()->new UserNotFoundException("User not found"));
    }

    public User updateUser(UUID id, User user) throws UserNotFoundException{
            userRepository.findById(id).orElseThrow(()->new UserNotFoundException("User not found. Please provide correct id"));
            user.setId(id);
            return userRepository.save(user);


    }

    public void deleteUser(UUID id)throws UserNotFoundException{
           userRepository.findById(id)
                   .orElseThrow(()->new UserNotFoundException("User not found in repository. Please provide correct id"));
            userRepository.deleteById(id);

    }

    public User findByUsername(String username){
        return userRepository.findUserByUsername(username);
    }
}
