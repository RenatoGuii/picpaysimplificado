package com.picpaysimplificado.controllers;

import com.picpaysimplificado.dtos.TransactionDTO;
import com.picpaysimplificado.entities.TransactionEntity;
import com.picpaysimplificado.services.TransactionService;
import com.picpaysimplificado.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Transactions", description = "APIs relacionadas a transações")
@RestController
@RequestMapping("transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @Autowired
    UserService userService;

    @Operation(summary = "Cria uma nova transação")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transação criada com sucesso!",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TransactionEntity.class))),
            @ApiResponse(responseCode = "400", description = "Entrada inválida",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<TransactionEntity> createTransaction(@RequestBody @Valid TransactionDTO data) {
        TransactionEntity transaction = transactionService.createTransaction(data);
        return ResponseEntity.status(HttpStatus.OK).body(transaction);
    }

    @Operation(summary = "Obter uma transação por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transação encontrada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TransactionEntity.class))),
            @ApiResponse(responseCode = "404", description = "Transação não encontrada",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<TransactionEntity> getOneTransaction(@PathVariable(value = "id") Long id) {
        TransactionEntity transaction = transactionService.getOneTransaction(id);
        return ResponseEntity.status(HttpStatus.OK).body(transaction);
    }

    @Operation(summary = "Obter todos as transações com paginação")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de transações",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TransactionEntity.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<TransactionEntity>> listTransactions(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5")  int size) {
        List<TransactionEntity> transactionList = transactionService.listTransactions(page, size);
        return ResponseEntity.status(HttpStatus.OK).body(transactionList);
    }
}
