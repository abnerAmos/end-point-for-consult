package com.example.demo.controller;

import com.example.demo.model.Client;
import com.example.demo.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/* Informa e transforma a classe em um Controller */
@RestController
@RequestMapping("/test")
public class GetController {

    @Autowired
    private ClientRepository clientRepository;

    /* Apenas um retorno em texto String */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public ResponseEntity<String> greeting() {
        return ResponseEntity.ok("Hello World"); // Forma de retorno de Status HTTP
    }

    /* Busca um objeto por ID */
    @ResponseStatus(HttpStatus.OK) // Forma de retorno de Status HTTP
    @GetMapping("/get/{id}")
    public Optional<Client> findById(@PathVariable("id") Integer id) {
        return clientRepository.findById(id);
    }

    /* Retorna a lista de Objetos */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/list")
    public List<Client> list() {
        return clientRepository.findAll();
    }

    /* Get para paginação:
    * Ao utilizar "Page" no tipo do Objeto, é possivel fazer paginação utilizando:
    * ?size=1 (quantidade de itens por pagina) e ?size=2&page=1 (numero da pagina)
    * O Spring fornece a lógica para aplicar */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/pageable")
    public Page<Client> listPageable(Pageable pageable) {
        return clientRepository.findAll(pageable);
    }

    /* Filtrando a busca de um Objeto com stream */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/find-with-param")
    public List<Client> findParam() {
        return list().stream().filter(e -> e.getAge() >= 50).collect(Collectors.toList());
    }

}