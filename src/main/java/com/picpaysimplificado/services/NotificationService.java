package com.picpaysimplificado.services;

import com.picpaysimplificado.dtos.NotificationDTO;
import com.picpaysimplificado.entities.user.UserEntity;
import com.picpaysimplificado.exceptions.TransactionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationService {

    @Autowired
    RestTemplate restTemplate;

    @Value("${CONFIRMATION_OF_NOTIFICATION_URL}")
    private String notificationUrl;

    public void sendNotification (UserEntity user, String msg) {
        String email = user.getEmail();
        NotificationDTO notificationRequest = new NotificationDTO(email, msg);

        // A Requisição de notificação do desafio parou de responder
//        ResponseEntity<String> notificationResponse = restTemplate.postForEntity(notificationUrl, notificationRequest, String.class);
//
//        if (!(notificationResponse.getStatusCode() == HttpStatus.OK)) {
//            throw new TransactionException("There was an error in the notification");
//        }

        System.out.println(email);
        System.out.println(msg);

    }

}
