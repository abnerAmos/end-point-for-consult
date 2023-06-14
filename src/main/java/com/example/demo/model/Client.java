package com.example.demo.model;

import com.example.demo.enums.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor

@Entity // Transforma a Classe em Bean do Spring, necessário para persistir dados.
@Table(name = "tb_client")   // Necessário informa o nome da tabela que irá persistir.
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @Min(18)
    private Integer age;

    @Email
    private String email;

    @Enumerated(EnumType.STRING) // Anotação para converter um ENUM para busca ou persistencia
    private Status status;

    @OneToMany(mappedBy = "client") // informa por qual atributo esta sendo mapeado mapeado
    @ToString.Exclude
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Client client = (Client) o;
        return getId() != null && Objects.equals(getId(), client.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
