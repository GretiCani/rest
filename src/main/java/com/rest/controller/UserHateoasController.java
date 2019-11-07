package com.rest.controller;

import com.rest.exception.UserNotFoundException;
import com.rest.model.Orders;
import com.rest.model.User;
import com.rest.repository.UserRepository;
import com.rest.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/hateoas/users")
public class UserHateoasController {

    private final UserRepository userRepository;
    private final UserService userService;


    @GetMapping("")
    public CollectionModel<User> users(){
        List<User> users = userService.getUsers();
        users.forEach(user -> {
            Link selfLink = WebMvcLinkBuilder.linkTo(this.getClass())
                    .slash(user.getId()).withSelfRel();

            CollectionModel<Orders> orders = WebMvcLinkBuilder
                    .methodOn(OrderHateoasContoller.class).getOrders(user.getId());

            Link linkOrders = WebMvcLinkBuilder.linkTo(orders).withRel("all-orders");
            user.add(linkOrders);
            user.add(selfLink);
        });
         CollectionModel<User> userResource = new CollectionModel<>(users);
        return userResource;
    }

    @GetMapping("/{id}")
    public EntityModel<User> findById(@PathVariable UUID id) {
        try {
            User user = userService.findUserById(id);
            Link selfLink = WebMvcLinkBuilder.linkTo(this.getClass()).slash(user.getId()).withSelfRel();
            user.add(selfLink);
            EntityModel<User> entityModel = new EntityModel<>(user);
            return entityModel;

        }catch (UserNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,ex.getMessage());
        }
    }

}
