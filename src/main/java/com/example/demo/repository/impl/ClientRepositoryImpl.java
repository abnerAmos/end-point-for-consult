package com.example.demo.repository.impl;

import com.example.demo.enums.Status;
import com.example.demo.model.Client;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ClientRepositoryImpl implements CustomClientRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Client> find(Long id, Status status) {

        var jpql = "from Client where id = :id and status = :status";

        return entityManager.createQuery(jpql, Client.class)
                .setParameter("id", id)
                .setParameter("status", status)
                .getResultList();
    }

    // Busca personalizada, util para não gerar grandes Querys dentro do repository.
}
