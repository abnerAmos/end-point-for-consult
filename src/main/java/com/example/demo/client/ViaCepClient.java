package com.example.demo.client;

import com.example.demo.exception.CepNotFoundException;
import com.example.demo.response.ViaCepResponse;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ViaCepClient {

    private final String url = "https://viacep.com.br/ws/";
    private final String uri = "/json";

    public ViaCepResponse getCep(String cep) {

        String path = url + cep + uri;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ViaCepResponse> response = restTemplate
                .getForEntity(path, ViaCepResponse.class);
        if(response.getStatusCode().isError())
            throw new CepNotFoundException("Cep inexistente");

        return response.getBody();
    }
}
