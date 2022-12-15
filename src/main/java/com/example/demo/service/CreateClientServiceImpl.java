package com.example.demo.service;

import com.example.demo.controller.request.Request;
import com.example.demo.model.Client;
import com.example.demo.model.Dependents;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.DependentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateClientServiceImpl {

    private final ClientRepository clientRepository;
    private final DependentsRepository dependentsRepository;

    public ResponseEntity<?> createClient(Request request) {

        Client client = Client.builder()
                .name(request.getName())
                .age(request.getAge())
                .email(request.getEmail())
                .status(request.getStatus())
                .build();
        Client saveClient = clientRepository.save(client);

        Dependents dependents = Dependents.builder()
                .name_dp(request.getName_dp())
                .age_dp(request.getAge_dp())
                .client(saveClient)
                .build();
        Dependents saveDependents = dependentsRepository.save(dependents);

        return new ResponseEntity<>(dependents, HttpStatus.OK);
    }

}