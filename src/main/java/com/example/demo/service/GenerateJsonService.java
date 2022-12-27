package com.example.demo.service;

import com.example.demo.model.Dependents;
import com.example.demo.repository.DependentsRepository;
import com.google.gson.Gson;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Service
public class GenerateJsonService {

    @Autowired
    DependentsRepository dependentsRepository;

    @SneakyThrows
    public ResponseEntity<?> downloadJson(Integer id, HttpServletResponse response) {
        Optional<Dependents> result = dependentsRepository.findById(id);        // Faz a busca do Objeto
        Dependents dependents = result.get();                                   // Armazena o resultado
        Gson gson = new Gson();                                                 // Instancia a Biblioteca do Google
        byte[] json = gson.toJson(dependents).getBytes();                       /* Transforma o Objeto em uma String
                                                                                no formato JSON, e converte em bytes em seguida */

        response.setContentType("application/json");                            // Seta o tipo de conteúdo
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename= " + "file_test_" + id + ".json";

        response.setHeader(headerKey, headerValue);

        ServletOutputStream output = response.getOutputStream();                // Cria uma saída
        output.write(json);                                                     // Informa o Objeto de saída

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
