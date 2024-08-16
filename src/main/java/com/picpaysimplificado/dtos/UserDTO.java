package com.picpaysimplificado.dtos;

import com.picpaysimplificado.entities.user.UserType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserDTO(@NotBlank String firstName, @NotBlank String lastName, @Email @NotBlank String email, @NotBlank String password, @NotBlank String document,
                      UserType userType) {
}
