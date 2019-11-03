package com.rest.controller;

import com.rest.model.User;
import com.rest.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    public User createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @GetMapping("/users/{id}")
    public Optional<User> findById(@PathVariable UUID id){
        return userService.findUserById(id);
    }

    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable UUID id, @RequestBody User user){
        return userService.updateUser(id,user);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable UUID id){
        userService.deleteUser(id);
    }

    @GetMapping("/users/byusername/{username}")
    public User findByUsername(@PathVariable String username){
        return userService.findByUsername(username);
    }

}
