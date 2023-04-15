package com.example.demo.repository.impl;

import com.example.demo.enums.Status;
import com.example.demo.model.Client;

import java.util.List;

public interface CustomClientRepository {

    List<Client> find(Long id, Status status);
}
