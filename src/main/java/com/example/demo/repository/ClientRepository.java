package com.example.demo.repository;

import com.example.demo.enums.Status;
import com.example.demo.model.Client;
import com.example.demo.repository.impl.CustomClientRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// Transforma a Classe em Bean do Spring, necessário para persistir dados.
@Repository
public interface ClientRepository extends JpaRepository<Client, Long>, CustomClientRepository {

    // Busca personalizada com Spring Data com Especificações JPA
    Optional<Client> findByStatusAndId(Status status, Integer id);

    // Busca que retorna apenas campos especifícos
    @Query(value = "select name, age from client where id = :id", nativeQuery = true)
    List<Object[]> buscar(@Param("id") int id);

}
