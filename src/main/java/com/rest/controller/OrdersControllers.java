package com.rest.controller;

import com.rest.exception.OrderNotFoundException;
import com.rest.exception.UserNotFoundException;
import com.rest.exception.UsernameNotFoundException;
import com.rest.model.Orders;
import com.rest.model.User;
import com.rest.repository.OrdersRepository;
import com.rest.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/users")
public class OrdersControllers {

    private final UserRepository userRepository;
    private final OrdersRepository ordersRepository;

    @GetMapping("/{userid}/orders")
    public List<Orders> getOrders(@PathVariable UUID userid) {
        User user = userRepository.findById(userid).orElseThrow(()->new UserNotFoundException("User not found in repository"));
        return user.getOrders();
    }

    @PostMapping("/{userid}/orders")
    public void createOrder(@PathVariable UUID userid, @RequestBody Orders order){
        User user = userRepository.findById(userid)
                .orElseThrow(()->new  UserNotFoundException("Can not create order. User not exist."));
        order.setUser(user);
        ordersRepository.save(order);
    }

    @GetMapping("/{userid}/orders/{orderid}")
    public Orders findOrderById(@PathVariable UUID userid, @PathVariable UUID orderid) {
        User user = userRepository.findById(userid)
                .orElseThrow(()-> new UsernameNotFoundException("Order does not exist. User not found"));
        Orders order = ordersRepository.findById(orderid)
                .orElseThrow(()->new OrderNotFoundException("Order not found"));
        if(order.getUser().equals(user))
            return order;
        else throw new OrderNotFoundException("Order not found");

    }

    @PutMapping("/{userid}/orders/{orderid}")
    public Orders updateOrder(@PathVariable UUID userid, @PathVariable UUID orderid,@RequestBody Orders order){
        User user = userRepository.findById(userid)
                .orElseThrow(()->new UsernameNotFoundException("Can not update order user does not exist"));
        Orders order1 = ordersRepository.findById(orderid)
                .orElseThrow(()->new OrderNotFoundException("Can not update order. Order does not exist"));
        order.setId(orderid);
        return ordersRepository.save(order);
    }

    @DeleteMapping("/{userid}/orders/{orderid}")
    public void deleteOrder(@PathVariable UUID userid,@PathVariable UUID orderid){
        User user = userRepository.findById(userid)
                .orElseThrow(()->new UserNotFoundException("Can not delete order. User does not exist."));
        Orders order = ordersRepository.findById(orderid)
                .orElseThrow(()->new OrderNotFoundException("Can not delete order. Order not found"));
        if(order.getUser().equals(user))
            ordersRepository.delete(order);
        else throw new OrderNotFoundException("Can not delete order. Deleting orders is permitted only to owners");
    }
}
