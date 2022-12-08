package com.example.demo.exception;

public class ClientNotFoundException extends RuntimeException{

    // Criação de uma Exception personalizada, extendendo RuntimeException
    public ClientNotFoundException(String message) {
        super(message);
    }
}
