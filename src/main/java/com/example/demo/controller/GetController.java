package com.example.demo.controller;

import com.example.demo.model.Address;
import com.example.demo.model.Client;
import com.example.demo.model.Dependents;
import com.example.demo.repository.AddressRepository;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.DependentsRepository;
import com.example.demo.service.CreateExcelService;
import com.example.demo.service.GenerateJsonService;
import com.example.demo.service.ReadAndSaveExcelService;
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
    private final ReadAndSaveExcelService readAndSaveExcelService;
    private final CreateExcelService createExcelService;
    private final DependentsRepository dependentsRepository;
    private final AddressRepository addressRepository;

    /* Apenas um retorno em texto String */
    @GetMapping
    public ResponseEntity<String> greeting() {
        return ResponseEntity.ok("Hello World"); // Forma de retorno de Status HTTP
    }

    /* Busca um objeto por ID */
    @ResponseStatus(HttpStatus.OK) // Forma de retorno de Status HTTP
    @GetMapping("/client/{id}")
    public Optional<Client> findClientById(@PathVariable("id") Long id) {
        return clientRepository.findById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/dependent/{id}")
    public Optional<Dependents> findDependentsById(@PathVariable("id") Integer id) {
        return dependentsRepository.findById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/address/{id}")
    public Optional<Address> findAddressById(@PathVariable("id") Long id) {
        return addressRepository.findById(id);
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
    @GetMapping(value = "/download-excel/{fileName}")
    public void downloadExcel(@PathVariable String fileName,
                                                      HttpServletResponse response) throws IOException {
        fileName = fileName + ".xlsx";
        createExcelService.createFile(fileName, clientRepository.findAll(), response);
    }

    /* Lendo um arquivo Excel, a partir de um ".xlsx",
    informando apenas o nome do arquivo */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/read-excel-and-save/{fileName}")
    public List<Client> readExcel(@PathVariable String fileName) throws IOException {
        String extension = ".xlsx";
        return readAndSaveExcelService.readFile(fileName + extension);
    }

    /*  Captura um Objeto e o converte em .JSON, e o armazena no Header
    como anexo (attachment), para Download.     */
    @GetMapping("/download-json/{id}")
    public ResponseEntity<?> downloadJson(@PathVariable Integer id, HttpServletResponse response) {
        return generateJsonService.downloadJson(id, response);
    }

    // Busca e retorna apenas campos especifícados na Query
    @GetMapping("/specs/{id}")
    public ResponseEntity<?> test(@PathVariable int id) {
        return ResponseEntity.ok(clientRepository.buscar(id));
    }

}
