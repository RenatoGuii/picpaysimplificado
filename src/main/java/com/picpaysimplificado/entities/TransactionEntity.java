package com.picpaysimplificado.entities;

import com.picpaysimplificado.entities.user.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.hateoas.RepresentationModel;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionEntity extends RepresentationModel<TransactionEntity> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime date;
    private BigDecimal val;

    @ManyToOne
    @JoinColumn(name = "id_payer")
    private UserEntity payer;

    @ManyToOne
    @JoinColumn(name = "id_payee")
    private UserEntity payee;
}
