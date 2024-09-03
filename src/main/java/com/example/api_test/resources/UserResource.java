package com.example.api_test.resources;

import com.example.api_test.domain.User;
import com.example.api_test.domain.dto.UserDTO;
import com.example.api_test.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserResource {

    @Autowired
    private ModelMapper mapper;
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Integer id) {
        User user = userService.findById(id);
        UserDTO userDTO = mapper.map(user, UserDTO.class);
        return ResponseEntity.ok(userDTO);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
        List<UserDTO> listDto = userService.findAll()
                .stream()
                .map(x -> mapper.map(x, UserDTO.class)).toList();
        return ResponseEntity.ok(listDto);
    }

    @PostMapping
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO obj) {
        User newObj = userService.create(obj);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newObj.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }
}
