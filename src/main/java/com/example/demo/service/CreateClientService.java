package com.example.demo.service;

import com.example.demo.request.RequestClient;
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
public class CreateClientService {

    private final ClientRepository clientRepository;
    private final DependentsRepository dependentsRepository;

    public ResponseEntity<?> createClient(RequestClient requestClient) {

        Client client = Client.builder()
                .name(requestClient.getName())
                .age(requestClient.getAge())
                .email(requestClient.getEmail())
                .status(requestClient.getStatus())
                .build();
        Client saveClient = clientRepository.save(client);

        Dependents dependents = Dependents.builder()
                .name_dp(requestClient.getName_dp())
                .age_dp(requestClient.getAge_dp())
                .client(saveClient)
                .build();
        Dependents saveDependents = dependentsRepository.save(dependents);

        return new ResponseEntity<>(dependents, HttpStatus.OK);
    }

}
