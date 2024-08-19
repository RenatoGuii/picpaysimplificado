package com.picpaysimplificado.entities.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity extends RepresentationModel<UserEntity> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String email;

    @JsonIgnore
    private String password;

    @JsonIgnore
    private String document; // Pode ser CPF ou CNPJ

    @Column(name = "user_type")
    @Enumerated(EnumType.STRING)
    private UserType userType;

    @JsonIgnore
    private BigDecimal balance;
}
