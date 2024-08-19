package com.picpaysimplificado.dtos;

import jakarta.validation.constraints.NotBlank;

public record NotificationDTO(@NotBlank String email, @NotBlank String msg) {
}
