package com.example.api_test.services.impl;

import com.example.api_test.domain.User;
import com.example.api_test.domain.dto.UserDTO;
import com.example.api_test.repositories.UserRepository;
import com.example.api_test.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceImplTest {

    public static final Integer ID = 1;
    public static final String NAME = "Teste";
    public static final String EMAIL = "email@email.com";
    public static final String PASSWORD = "123";
    @InjectMocks
    private UserServiceImpl service;
    @Mock
    private UserRepository repository;
    @Mock
    private ModelMapper mapper;
    private User user;
    private UserDTO userDTO;
    private Optional<User> optionalUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void whenFindByIdThenReturnUserInstance() {
        when(repository.findById(anyInt())).thenReturn(optionalUser);

        User response = service.findById(ID);

        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());
    }

    @Test
    void whenFindByIdThenReturnObjectNotFoundException() {
        ObjectNotFoundException ex = assertThrows(ObjectNotFoundException.class, () -> service.findById(ID));

        assertEquals(ObjectNotFoundException.class, ex.getClass());
        assertEquals("Objeto não encontrado", ex.getMessage());
    }

    @Test
    void whenFindAllThenReturnListOfUsers() {
        when(repository.findAll()).thenReturn(List.of(user));

        List<User> list = service.findAll();

        assertNotNull(list);
        assertEquals(1, list.size());
        assertEquals(User.class, list.get(0).getClass());
        assertEquals(ID, list.get(0).getId());
        assertEquals(NAME, list.get(0).getName());
        assertEquals(EMAIL, list.get(0).getEmail());
        assertEquals(EMAIL, list.get(0).getEmail());
        assertEquals(PASSWORD, list.get(0).getPassword());
    }

    @Test
    void whenCreatedThenReturnSuccess() {
        when(repository.save(any())).thenReturn(user);

        User response = service.create(userDTO);

        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(PASSWORD, response.getPassword());
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    private void startUser() {
        user = new User(ID, NAME, EMAIL, PASSWORD);
        userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);
        optionalUser = Optional.of(new User(ID, NAME, EMAIL, PASSWORD));
    }
}