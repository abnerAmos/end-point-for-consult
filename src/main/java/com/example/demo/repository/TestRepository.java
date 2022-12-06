package com.example.demo.repository;

import com.example.demo.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Transforma a Classe em Bean do Spring, necessário para persistir dados.
@Repository
public interface TestRepository extends JpaRepository<Client, Integer> {

}
