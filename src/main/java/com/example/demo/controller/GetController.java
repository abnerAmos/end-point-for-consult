package com.example.demo.controller;

import com.example.demo.model.Client;
import com.example.demo.repository.ClientRepository;
import com.example.demo.service.CreateExcelService;
import com.example.demo.service.GenerateJsonService;
import com.example.demo.service.ReadExcelService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class GetController {

    private final GenerateJsonService generateJsonService;
    private final ClientRepository clientRepository;
    private final ReadExcelService readExcelService;
    private final CreateExcelService createExcelService;

    /* Apenas um retorno em texto String */
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
    @GetMapping("/find-stream")
    public List<Client> findParam() {
        List<Client> listClient = clientRepository.findAll();
        return listClient.stream().filter(e -> e.getAge() >= 50).collect(Collectors.toList());
    }

    /* Criando um arquivo Excel, a partir dos dados em Banco,
    informando apenas um nome para o arquivo */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/create-excel/{fileName}")
    public List<Client> generateExcel(@PathVariable String fileName) throws IOException {
        String extension = ".xlsx";
        createExcelService.createFile(fileName + extension, clientRepository.findAll());
        return clientRepository.findAll();
    }

    /* Lendo um arquivo Excel, a partir de um ".xlsx",
    informando apenas o nome do arquivo */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/read-excel/{fileName}")
    public List<Client> readExcel(@PathVariable String fileName) throws IOException {
        String extension = ".xlsx";
        return readExcelService.readFile(fileName + extension);
    }

    /*  Captura um Objeto e o converte em .JSON, e o armazena no Header
    como anexo (attachment), para Download.     */
    @GetMapping("/download/{id}")
    public ResponseEntity<?> download(@PathVariable Integer id, HttpServletResponse response) {
        return generateJsonService.downloadJson(id, response);
    }

    // Busca e retorna apenas campos especifícados na Query
    @GetMapping("/specs/{id}")
    public ResponseEntity<?> test(@PathVariable int id) {
        return ResponseEntity.ok(clientRepository.buscar(id));
    }

}
