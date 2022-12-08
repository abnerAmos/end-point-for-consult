package com.example.demo.controller;

import com.example.demo.exception.ClientNotFoundException;
import com.example.demo.model.Client;
import com.example.demo.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("test-post")
public class PostController {

    @Autowired
    TestRepository testRepository;

    /* Inserindo dados na tabela
    * Necessário no corpo do Json, informar os dados a serem inclusos no Banco conforme Model
    * Na construção do objeto fui Utilizado @Builder */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    public Client createClient(@RequestBody Client request) {

        Client client = Client.builder()
                .name(request.getName())
                .age(request.getAge())
                .email(request.getEmail())
                .status(request.getStatus())
                .build();

        testRepository.save(client);
        return client;
    }

    /* Filtrando uma busca com Post, informando o Dado a ser buscado no postman */
    @PostMapping("/find-by-name")
    public Client findNameOrEmail(@RequestBody Client filtro) {
        return testRepository.findByNameOrEmail(filtro.getName(), filtro.getEmail()).orElseThrow(() -> {
            throw new ClientNotFoundException("CLIENTE NAO ENCONTRADO");
        });
    }
}

