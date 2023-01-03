package com.example.demo.request;

import com.example.demo.enums.Status;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/* Classe dentro de classe para moldagem de JSON.

* Não é a forma recomendada a ser usada,
* e sim 2 classes que implementam uma a outra */

@Getter
@Setter
@Builder
public class JsonRequestClient implements Serializable {

    private String name;
    private Integer age;
    private String email;
    private Status status;
    private List<JsonRequestDependent> dependents;

    @Data
    @Builder
    public static class JsonRequestDependent {
        private String name_dp;
        private Integer age_dp;
    }
}
