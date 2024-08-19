package com.picpaysimplificado.dtos;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record TransactionDTO(@NotNull int id_payer, @NotNull int id_payee, @NotNull BigDecimal val) {
}
