package com.example.demo.exception;

public class CepNotFoundException extends RuntimeException{

    // Criação de uma Exception personalizada, extendendo RuntimeException
    public CepNotFoundException(String message) {
        super(message);
    }
}
