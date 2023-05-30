package com.example.demo.controller;

import com.example.demo.request.RequestClient;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.DependentsRepository;
import com.example.demo.service.CreateClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("test-post")
@RequiredArgsConstructor
public class PostController {

    private final ClientRepository clientRepository;
    private final DependentsRepository dependentsRepository;
    private final CreateClientService createClientService;

    /* Inserindo dados na tabela
    * Necessário no corpo do Json, informar os dados a serem inclusos no Banco conforme Model
    * Na construção do objeto foi Utilizado @Builder do Lombok */

    /* @Valid faz a validação dos campos antes de chamar o método em si.
        OBS.: @Valid confere as validações no Objeto chamado no parâmentro do endpoint/método
        */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    public ResponseEntity<?> createClient(@RequestBody @Valid RequestClient requestClient) {
        return createClientService.createClient(requestClient);
    }

}

