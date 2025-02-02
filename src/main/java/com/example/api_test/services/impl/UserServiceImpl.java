package com.example.api_test.services.impl;

import com.example.api_test.domain.User;
import com.example.api_test.domain.dto.UserDTO;
import com.example.api_test.repositories.UserRepository;
import com.example.api_test.services.UserService;
import com.example.api_test.services.exceptions.DataIntegrityViolationException;
import com.example.api_test.services.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private ModelMapper mapper;
    @Autowired
    private UserRepository userRepository;

    @Override
    public User findById(Integer id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User create(UserDTO obj) {
        findByEmail(obj);
        return userRepository.save(mapper.map(obj, User.class));
    }

    @Override
    public User update(UserDTO obj) {
        findByEmail(obj);
        return userRepository.save(mapper.map(obj, User.class));
    }

    @Override
    public void delete(Integer id) {
        findById(id);
        userRepository.deleteById(id);
    }

    private void findByEmail(UserDTO obj) {
        Optional<User> user = userRepository.findByEmail(obj.getEmail());

        if (user.isPresent() && !user.get().getId().equals(obj.getId())) {
            throw new DataIntegrityViolationException("Email já cadastrado");
        }
    }
}
