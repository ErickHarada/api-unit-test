package com.example.api_test.services;

import com.example.api_test.domain.User;

public interface UserService {

    User findById(Integer id);
}
