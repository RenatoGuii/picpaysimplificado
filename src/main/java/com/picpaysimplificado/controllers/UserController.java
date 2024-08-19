package com.picpaysimplificado.controllers;

import com.picpaysimplificado.dtos.UserDTO;
import com.picpaysimplificado.services.UserService;
import com.picpaysimplificado.entities.user.UserEntity;
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

@Tag(name = "Users", description = "APIs relacionadas a usuários")
@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    UserService userService;

    @Operation(summary = "Registra um novo usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário criado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserEntity.class))),
            @ApiResponse(responseCode = "400", description = "Entrada inválida",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content)
    })
    @PostMapping("/register")
    public ResponseEntity<UserEntity> postUser(@RequestBody @Valid UserDTO data) {
        UserEntity newUser = userService.postUser(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @Operation(summary = "Busca um usuário por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Encontrei o usuário",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserEntity.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> getOneuser(@PathVariable(value = "id") Long id) {
        UserEntity user = userService.getOneUser(id);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @Operation(summary = "Obter todos os usuários com paginação")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de usuários",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserEntity.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<UserEntity>> getAllUsers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
        List<UserEntity> listUsers = userService.getAllUsers(page, size);
        return ResponseEntity.status(HttpStatus.OK).body(listUsers);
    }

}
