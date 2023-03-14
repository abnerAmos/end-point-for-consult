package com.example.demo.service;

import com.example.demo.client.ViaCepClient;
import com.example.demo.model.Address;
import com.example.demo.model.Client;
import com.example.demo.model.Dependents;
import com.example.demo.repository.AddressRepository;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.DependentsRepository;
import com.example.demo.request.RequestClient;
import com.example.demo.response.CreateClientResponse;
import com.example.demo.response.ViaCepResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CreateClientService {

    private final ClientRepository clientRepository;
    private final DependentsRepository dependentsRepository;
    private final AddressRepository addressRepository;
    private final ViaCepClient viaCepClient;

    public ResponseEntity<?> createClient(RequestClient requestClient) {

        Client client = new Client();
                client.setName(requestClient.getName());
                client.setAge(requestClient.getAge());
                client.setEmail(requestClient.getEmail());
                client.setStatus(requestClient.getStatus());
        clientRepository.save(client);

        List<Dependents> dependents = new ArrayList<>();

        // for para iterar um array de Objetos
        for (int i = 0; i < requestClient.getDependents().size(); i++) {
            Dependents dependent = new Dependents();
            dependent.setName_dp(requestClient.getDependents().get(i).getName_dp());
            dependent.setAge_dp(requestClient.getDependents().get(i).getAge_dp());
            dependent.setClient(client);
            dependents.add(dependent);
        }

        dependentsRepository.saveAll(dependents);

        ViaCepResponse viacep = viaCepClient.getCep(requestClient.getCep());
        Address address = Address.builder()
                .cep(requestClient.getCep())
                .address(viacep.getLogradouro())
                .number(requestClient.getNumber())
                .district(viacep.getBairro())
                .city(viacep.getLocalidade())
                .state(viacep.getUf())
                .client(client)
                .build();
        addressRepository.save(address);

        return new ResponseEntity<>(createResponse(client, dependents, address), HttpStatus.OK);
    }

    public CreateClientResponse createResponse(Client client, List<Dependents> dependents, Address address) {

        CreateClientResponse response = new CreateClientResponse();

        response.setName(client.getName());
        response.setAge(client.getAge());
        response.setEmail(client.getEmail());
        response.setStatus(client.getStatus());
        response.setDependents(dependents);
        response.setAddress(address);
        return response;
    }

}
