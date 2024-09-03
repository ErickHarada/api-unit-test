package com.example.api_test.services;

import com.example.api_test.domain.User;
import com.example.api_test.domain.dto.UserDTO;

import java.util.List;

public interface UserService {

    User findById(Integer id);
    List<User> findAll();
    User create(UserDTO obj);
}
