package com.example.demo.model;

import com.example.demo.enums.Status;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor

@Entity // Transforma a Classe em Bean do Spring, necessário para persistir dados.
@Table(name = "tb_client")   // Necessário informa o nome da tabela que irá persistir.
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer age;
    private String email;
    @Enumerated(EnumType.STRING) // Anotação para converter um ENUM para busca ou persistencia
    private Status status;

    @OneToMany(mappedBy = "client") // informa por qual atributo esta sendo mapeado mapeado
    private List<Dependents> dependents;

    @OneToOne(mappedBy = "client")
    private Address address;

    public Client(Long id, String name, Integer age, String email, Status status) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.status = status;
    }
}
