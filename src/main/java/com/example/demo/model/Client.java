package com.example.demo.model;

import com.example.demo.enums.Status;
import lombok.*;

import javax.persistence.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity // Transforma a Classe em Bean do Spring, necessário para persistir dados.
@Table(name = "test")   // Necessário informa o nome da tabela que irá persistir.
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer age;
    private String email;
    @Enumerated(EnumType.STRING) // Anotação para converter um ENUM para busca ou persistencia
    private Status status;
}
