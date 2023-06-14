package com.example.demo.service;

import com.example.demo.enums.Status;
import com.example.demo.exception.ClientNotFoundException;
import com.example.demo.model.Client;
import com.example.demo.repository.ClientRepository;
import com.example.demo.request.RequestClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateClientServiceImpl {

    private final ClientRepository clientRepository;

    public ResponseEntity<?> updateName(Integer id, RequestClient request) {

        Client client = clientRepository.findByStatusAndId(Status.ATIVO, id)
                .orElseThrow(() -> new ClientNotFoundException("CLIENTE NÃO ENCONTRADO!"));

        client.setName(request.getName());
        clientRepository.save(client);

        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    public ResponseEntity<?> updateClient(Integer id, RequestClient request) {

        Client client = clientRepository.findByStatusAndId(Status.ATIVO, id)
                .orElseThrow(() -> new ClientNotFoundException("CLIENTE NÃO ENCONTRADO!"));

        client.setName(request.getName());
        client.setAge(request.getAge());
        client.setEmail(request.getEmail());
        clientRepository.save(client);

        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    public Client updateClientMock(Long id, RequestClient request) {

        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException("CLIENTE NÃO ENCONTRADO!"));

        client.setName(request.getName());
        client.setAge(request.getAge());
        client.setEmail(request.getEmail());
        clientRepository.save(client);

        return client;
    }
}
