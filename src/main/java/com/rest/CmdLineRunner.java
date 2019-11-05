package com.rest;

import com.rest.model.Orders;
import com.rest.model.User;
import com.rest.repository.OrdersRepository;
import com.rest.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class CmdLineRunner implements CommandLineRunner
{
    private final UserRepository userRepository;
    private final OrdersRepository ordersRepository;

    @Override
    public void run(String... args) throws Exception {
       User user1 = userRepository.save(new User("GretiCani","Greti1","Cani12","greticani@yahoo.com","admin","ssn100"));
       User user2 = userRepository.save(new User("DionisCani","Dionis","Cani12","dioniscani@yahoo.com","manager","ssn101"));
       User user3 = userRepository.save(new User("GentianaCani","Gentiana","Cani12","gentianacani@yahoo.com","manager","ssn102"));

       Orders order1 =  ordersRepository.save(new Orders("Order 001",user1));
       Orders order2 =  ordersRepository.save(new Orders("Order 002",user2));
       Orders order3 =  ordersRepository.save(new Orders("Order 003",user3));
       Orders order4=  ordersRepository.save(new Orders("Order 004",user1));
        Orders order5 =  ordersRepository.save(new Orders("Order 005",user2));
        Orders order6 =  ordersRepository.save(new Orders("Order 006",user3));
        Orders order7 =  ordersRepository.save(new Orders("Order 007",user1));
        Orders order8 =  ordersRepository.save(new Orders("Order 008",user2));
        Orders order9 =  ordersRepository.save(new Orders("Order 009",user3));
    }
}
