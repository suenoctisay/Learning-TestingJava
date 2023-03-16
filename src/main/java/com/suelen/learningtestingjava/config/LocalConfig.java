package com.suelen.learningtestingjava.config;

import com.suelen.learningtestingjava.domain.Users;
import com.suelen.learningtestingjava.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("local")
public class LocalConfig {

    @Autowired
    private UserRepository repository;
    
    @Bean
    public void startDB(){
        Users user01 = new Users(null, "sue", "sue@mail", "123");
        Users user02 = new Users(null, "ellie", "ellie@mail", "123");

        repository.saveAll(List.of(user01, user02));
    }
}
