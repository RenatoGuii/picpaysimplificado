package com.picpaysimplificado.services;

import com.picpaysimplificado.controllers.UserController;
import com.picpaysimplificado.dtos.UserDTO;
import com.picpaysimplificado.exceptions.EntityNotFoundException;
import com.picpaysimplificado.repositories.UserRepository;
import com.picpaysimplificado.entities.user.UserEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserEntity postUser (UserDTO data) {
        UserEntity newUser = new UserEntity();
        BeanUtils.copyProperties(data, newUser);
        newUser.setBalance(BigDecimal.ZERO);
        newUser.setPassword(new BCryptPasswordEncoder().encode(data.password()));
        userRepository.save(newUser);
        return newUser;
    }

    public UserEntity getOneUser(Long id) {
        Optional<UserEntity> user = userRepository.findById(id.intValue());

        if (user.isEmpty()) {
            throw new EntityNotFoundException("There is no user with this identification");
        }

        return user.get();
    }

    public List<UserEntity> getAllUsers (int page, int size) {
        List<UserEntity> userList = userRepository.findAll(PageRequest.of(page, size)).getContent();

        if (userList.isEmpty()) {
            throw new EntityNotFoundException("No user registration exists");
        }

        for (UserEntity user : userList) {
            user.add(linkTo(methodOn(UserController.class).getOneuser(user.getId())).withSelfRel());
        }

        return userList;
    }

}
