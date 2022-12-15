package com.example.demo.repository;

import com.example.demo.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// Transforma a Classe em Bean do Spring, necessário para persistir dados.
@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    // Busca personalizada com Spring Data com Especificações JPA
    Optional<Client> findByNameOrEmail(String name, String email);

}
