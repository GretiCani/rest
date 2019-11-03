package com.rest;

import com.rest.model.User;
import com.rest.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class CmdLineRunner implements CommandLineRunner
{
    private final UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        userRepository.save(new User("GretiCani","Greti","Cani","greticani@yahoo.com","admin","ssn100"));
        userRepository.save(new User("DionisCani","Dionis","Cani","dioniscani@yahoo.com","manager","ssn101"));
        userRepository.save(new User("GentianaCani","Gentiana","Cani","gentianacani@yahoo.com","manager","ssn102"));
    }
}
