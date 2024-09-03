package com.example.api_test.services;

import com.example.api_test.domain.User;

import java.util.List;

public interface UserService {

    User findById(Integer id);

    List<User> findAll();
}
