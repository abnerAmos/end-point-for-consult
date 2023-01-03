package com.example.demo.service;

import com.example.demo.enums.Status;
import com.example.demo.model.Client;
import com.example.demo.model.Dependents;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.DependentsRepository;
import com.example.demo.request.JsonRequestClient;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GenerateJsonService {

    private final DependentsRepository dependentsRepository;
    private final ClientRepository clientRepository;

    @SneakyThrows
    public ResponseEntity<?> downloadJson(Integer id, HttpServletResponse response) {

        Client client = clientRepository.findByStatusAndId(Status.ATIVO, id);
        if (client == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("CLIENTE NÃO ENCONTRADO");

        List<Dependents> dependent = dependentsRepository
                .findByClient(client);               // Faz a busca a vinculada com cliente por FK

        List<JsonRequestClient.JsonRequestDependent> requestDependents = new ArrayList<>(); // Cria uma Listagem de Dependentes
        for (Dependents request : dependent){                                       /* Armazena Todos os Dependentes
                                                                              vinculados existenstes ao ID Informado*/
            requestDependents.add(JsonRequestClient.JsonRequestDependent.builder()
                            .name_dp(request.getName_dp())
                            .age_dp(request.getAge_dp())
                            .build());
        }

        JsonRequestClient requestClient = JsonRequestClient.builder()       // Armazena e molda o resultado
                .name(client.getName())
                .age(client.getAge())
                .email(client.getEmail())
                .status(client.getStatus())
                .dependents(requestDependents)
                .build();

        Gson gson = new Gson();                                                 // Instancia a Biblioteca do Google (IGNORA CAMPOS NULOS)
     // Gson gson = new GsonBuilder().serializeNulls().create();                // Não Ignora Campos Nulos
        byte[] json = gson.toJson(requestClient).getBytes();                       /* Transforma o Objeto em uma String
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
