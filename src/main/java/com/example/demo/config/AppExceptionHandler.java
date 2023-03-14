package com.example.demo.config;

import com.example.demo.exception.CepNotFoundException;
import com.example.demo.exception.ClientNotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)      // Prioriza o Interceptador (Erros devem ser tratos prioritariamente).
@ControllerAdvice                       // É um Interceptador, ele capta a exception no meio do "fluxo", e trata.
public class AppExceptionHandler {

    @ExceptionHandler(ClientNotFoundException.class)    // Informa qual Classe Exception será tratada.
    public ResponseEntity<String> clientNotFoundException(ClientNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());      // Retorno HTTP junto com a Menssagem informada no Controller
    }

    @ExceptionHandler(CepNotFoundException.class)
    public ResponseEntity<String> cepNotFoundException(CepNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

}

