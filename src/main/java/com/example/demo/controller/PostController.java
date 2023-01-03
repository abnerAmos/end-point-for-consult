package com.example.demo.controller;

import com.example.demo.request.RequestClient;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.DependentsRepository;
import com.example.demo.service.CreateClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("test-post")
@RequiredArgsConstructor
public class PostController {

    private final ClientRepository clientRepository;
    private final DependentsRepository dependentsRepository;
    private final CreateClientService createClientService;

    /* Inserindo dados na tabela
    * Necessário no corpo do Json, informar os dados a serem inclusos no Banco conforme Model
    * Na construção do objeto fui Utilizado @Builder */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    public ResponseEntity<?> createClient(@RequestBody RequestClient requestClient) {
        return createClientService.createClient(requestClient);
    }

}

