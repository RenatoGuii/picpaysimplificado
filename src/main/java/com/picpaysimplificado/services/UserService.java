package com.picpaysimplificado.services;

import com.picpaysimplificado.dtos.UserDTO;
import com.picpaysimplificado.entities.user.UserType;
import com.picpaysimplificado.exceptions.InvalidTypeUserException;
import com.picpaysimplificado.repositories.UserRepository;
import com.picpaysimplificado.entities.user.UserEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

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

}
