package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity // Transforma a Classe em Bean do Spring, necessário para persistir dados.
@Table(name = "tb_dependents")   // Necessário informa o nome da tabela que irá persistir.
public class Dependents implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name_dp;
    private Integer age_dp;

    /* O Lado esquerdo "ONE" é o da Classe <-
        O Lado direito "ONE" é o da Entidade -> */
    @JsonIgnore // Ignora o campo na construção do Json, caso não inserido gera um loop na resposta, pois uma classe chama a outra
    @ManyToOne // Informando qual o tipo de relação da tabela
    @JoinColumn(name = "client_id", referencedColumnName = "id") // "name" é a FK, "reference..." é a Referencia
    private Client client;
}
