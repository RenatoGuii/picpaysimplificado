package com.picpaysimplificado.services;

import com.picpaysimplificado.controllers.TransactionController;
import com.picpaysimplificado.dtos.TransactionDTO;
import com.picpaysimplificado.entities.TransactionEntity;
import com.picpaysimplificado.entities.user.UserEntity;
import com.picpaysimplificado.entities.user.UserType;
import com.picpaysimplificado.exceptions.EntityNotFoundException;
import com.picpaysimplificado.exceptions.TransactionException;
import com.picpaysimplificado.repositories.TransactionRepository;
import com.picpaysimplificado.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    NotificationService notificationService;

    @Autowired
    RestTemplate restTemplate;

    @Value("${AUTHORIZATION_TRANSACTION_URL}")
    private String authorizationUrl;

    @Transactional
    public TransactionEntity createTransaction(TransactionDTO data) {
        Optional<UserEntity> payer = userRepository.findById(data.id_payer());
        Optional<UserEntity> payee = userRepository.findById(data.id_payee());
        if (payer.isEmpty()) {
            throw new TransactionException("There is no payer with the id value entered");
        }
        if (payee.isEmpty()) {
            throw new TransactionException("There is no payee with the id value entered");
        }
        if (payer.get().getUserType() == UserType.MERCHANT) {
            throw new TransactionException("Merchants can't make transfers");
        }
        if (payer.get().getBalance().compareTo(data.val()) == -1 ) {
            throw new TransactionException("Your balance is insufficient for a transaction with the amount you entered");
        }

        TransactionEntity transaction = new TransactionEntity();
        transaction.setDate(LocalDateTime.now());
        transaction.setPayee(payee.get());
        transaction.setPayer(payer.get());
        transaction.setVal(data.val());

        transactionRepository.save(transaction);

        payer.get().setBalance(payer.get().getBalance().subtract(data.val()));
        payee.get().setBalance(payee.get().getBalance().add(data.val()));

        userRepository.save(payer.get());
        userRepository.save(payee.get());

        notificationService.sendNotification(payer.get(), "Transfer made successfully");
        notificationService.sendNotification(payee.get(), "You have received a transfer of " + payer.get().getFirstName() + " " + payer.get().getLastName() + " in the amount of " + data.val());

        return transaction;
    }

    public TransactionEntity getOneTransaction(Long id) {
        Optional<TransactionEntity> transaction = transactionRepository.findById(id.intValue());
        if (transaction.isEmpty()) {
            throw new EntityNotFoundException("There is no transaction with the quoted identifier");
        }
        return transaction.get();
    }

    public List<TransactionEntity> listTransactions(int page, int size) {
        List<TransactionEntity> transactionList = transactionRepository.findAll(PageRequest.of(page, size)).getContent();
        if (transactionList.isEmpty()) {
            throw new EntityNotFoundException("No transaction records exist");
        }
        for (TransactionEntity transaction : transactionList) {
            transaction.add(linkTo(methodOn(TransactionController.class).getOneTransaction(transaction.getId())).withSelfRel());
        }
        return transactionList;
    }

    public boolean authorizeTransaction(UserEntity user, BigDecimal val) {
        ResponseEntity<Map> authorizationResponse = restTemplate.getForEntity(authorizationUrl, Map.class);

        if (authorizationResponse.getStatusCode() == HttpStatus.OK) {
            String msg = (String) authorizationResponse.getBody().get("status");
            return "success".equalsIgnoreCase(msg);
        } else {
            return false;
        }
    }

}
