package com.rest.controller;

import com.rest.exception.UserNotFoundException;
import com.rest.model.Orders;
import com.rest.model.User;
import com.rest.repository.OrdersRepository;
import com.rest.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/hateoas/orders")
public class OrderHateoasContoller {

    public final OrdersRepository ordersRepository;
    public final UserRepository userRepository;

    @GetMapping("/{userid}/orders")
    public CollectionModel<Orders> getOrders(@PathVariable UUID userid) {
        User user = userRepository.findById(userid).orElseThrow(()->new UserNotFoundException("User not found in repository"));
        List<Orders> orders = user.getOrders();
        CollectionModel<Orders> entityOrders  = new CollectionModel<>(orders);
        return entityOrders;
    }



}
