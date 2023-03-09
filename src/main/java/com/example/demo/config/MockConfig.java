package com.example.demo.config;

import com.example.demo.enums.Status;
import com.example.demo.model.Address;
import com.example.demo.model.Client;
import com.example.demo.model.Dependents;
import com.example.demo.repository.AddressRepository;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.DependentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
@Profile("test")
public class MockConfig implements CommandLineRunner {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private DependentsRepository dependentsRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public void run(String... args) throws Exception {

        Client c1 = new Client(null, "João das Couves", 30, "fulano@email.com", Status.ATIVO);

        Dependents d1 = new Dependents(null, "Joãozinho das Couves", 10, c1);
        Dependents d2 = new Dependents(null, "Mariazinha das Couves", 8, c1);

        Address a1 = new Address(null, "São Paulo", "SP", "Rua das Saladas", "321-B", "Bairro dos Legumes", "01234-000", c1);

        clientRepository.saveAll(Arrays.asList(c1));
        dependentsRepository.saveAll(Arrays.asList(d1, d2));
        addressRepository.saveAll(Arrays.asList(a1));
    }
}
