package com.example.demo.response;

import lombok.Data;

@Data
public class ViaCepResponse {

    private String localidade;
    private String uf;
    private String logradouro;
    private String bairro;
    private String cep;
}
