package com.example.api_test.config;

import com.example.api_test.domain.User;
import com.example.api_test.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("local")
public class LocalConfig {

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void startDB() {
        User u1 = new User(null, "Nome Teste1", "email1@email.com", "123");
        User u2 = new User(null, "Nome Teste2", "email2@email.com", "123");
        userRepository.saveAll(List.of(u1, u2));
    }
}
