package com.example.demo.config;

import com.example.demo.exception.CepNotFoundException;
import com.example.demo.exception.ClientNotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@Order(Ordered.HIGHEST_PRECEDENCE)      // Prioriza o Interceptador (Erros devem ser tratos prioritariamente).
@RestControllerAdvice                       // É um Interceptador, ele capta a exception no meio do "fluxo", e trata.
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ClientNotFoundException.class)    // Informa qual Classe Exception será tratada.
    public ResponseEntity<String> clientNotFoundException(ClientNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());      // Retorno HTTP junto com a Menssagem informada no Controller
    }

    @ExceptionHandler(CepNotFoundException.class)
    public ResponseEntity<String> cepNotFoundException(CepNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    // ----------------------- Validação e retorno de campos inválidos utilizando Bean Validation -----------------------

    @Override   // Possui a anotação @ExceptionHandler que é responsável por capturar a exception quando lançada.
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {

        List<ErrorResponse.ErrorObject> errors = getErrors(ex);
        ErrorResponse errorResponse = getErrorResponse(ex, status, errors);
        return new ResponseEntity<>(errorResponse, status);
    }

    private List<ErrorResponse.ErrorObject> getErrors(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getFieldErrors().stream()
                .map(error -> new ErrorResponse.ErrorObject(error.getDefaultMessage(), error.getField(),
                        error.getRejectedValue()))
                .collect(Collectors.toList());
    }

    private ErrorResponse getErrorResponse(MethodArgumentNotValidException ex, HttpStatus status,
                                           List<ErrorResponse.ErrorObject> errors) {
        return new ErrorResponse("Requisição possuí campos inválidos", status.value(),
                status.getReasonPhrase(), ex.getBindingResult().getObjectName(), errors);
    }
}

