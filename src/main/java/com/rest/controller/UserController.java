package com.rest.controller;

import com.rest.exception.UserExistException;
import com.rest.exception.UserNotFoundException;
import com.rest.model.User;
import com.rest.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public List<User> users(){
        return userService.getUsers();
    }

    @PostMapping("/users")
    public ResponseEntity<Void> createUser(@Valid @RequestBody User user, UriComponentsBuilder builder){
        try {
            userService.createUser(user);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(builder.path("/users/{id}").buildAndExpand(user.getId()).toUri());
            return new ResponseEntity<>(headers,HttpStatus.CREATED);
        }catch (UserExistException ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,ex.getMessage());
        }
    }

    @GetMapping("/users/{id}")
    public User findById(@PathVariable UUID id) {
        try {
            return userService.findUserById(id);

        }catch (UserNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,ex.getMessage());
        }
    }

    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable UUID id, @RequestBody User user){
        try {
            return userService.updateUser(id,user);
        }catch (UserNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,ex.getMessage());
        }
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable UUID id){
        try {
            userService.deleteUser(id);
        }catch (UserNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,ex.getMessage());
        }
    }

    @GetMapping("/users/byusername/{username}")
    public User findByUsername(@PathVariable String username){
        return userService.findByUsername(username);
    }

}
