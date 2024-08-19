package com.picpaysimplificado.controllers;

import com.picpaysimplificado.dtos.TransactionDTO;
import com.picpaysimplificado.entities.TransactionEntity;
import com.picpaysimplificado.services.TransactionService;
import com.picpaysimplificado.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<TransactionEntity> createTransaction(@RequestBody @Valid TransactionDTO data) {
        TransactionEntity transaction = transactionService.createTransaction(data);
        return ResponseEntity.status(HttpStatus.OK).body(transaction);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionEntity> getOneTransaction(@PathVariable(value = "id") Long id) {
        TransactionEntity transaction = transactionService.getOneTransaction(id);
        return ResponseEntity.status(HttpStatus.OK).body(transaction);
    }

    @GetMapping
    public ResponseEntity<List<TransactionEntity>> listTransactions(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5")  int size) {
        List<TransactionEntity> transactionList = transactionService.listTransactions(page, size);
        return ResponseEntity.status(HttpStatus.OK).body(transactionList);
    }

}
