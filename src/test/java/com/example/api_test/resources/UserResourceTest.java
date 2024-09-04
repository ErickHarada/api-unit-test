package com.example.api_test.resources;

import com.example.api_test.domain.User;
import com.example.api_test.domain.dto.UserDTO;
import com.example.api_test.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class UserResourceTest {

    public static final Integer ID = 1;
    public static final String NAME = "Teste";
    public static final String EMAIL = "email@email.com";
    public static final String PASSWORD = "123";
    @InjectMocks
    private UserResource resource;
    @Mock
    private UserService service;
    @Mock
    private ModelMapper mapper;
    private User user;
    private UserDTO userDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void findById() {
    }

    private void startUser() {
        user = new User(ID, NAME, EMAIL, PASSWORD);
        userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);
    }
}