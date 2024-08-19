package com.picpaysimplificado.controllers;

import com.picpaysimplificado.dtos.UserDTO;
import com.picpaysimplificado.services.UserService;
import com.picpaysimplificado.entities.user.UserEntity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserEntity> postUser(@RequestBody @Valid UserDTO data) {
        UserEntity newUser = userService.postUser(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> getOneuser(@PathVariable(value = "id") Long id) {
        UserEntity user = userService.getOneUser(id);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @GetMapping
    public ResponseEntity<List<UserEntity>> getAllUsers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
        List<UserEntity> listUsers = userService.getAllUsers(page, size);
        return ResponseEntity.status(HttpStatus.OK).body(listUsers);
    }

}
