package com.example.demo.request;

import com.example.demo.enums.Status;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class RequestClient implements Serializable {

    // Captura a mensagem personalizada em "ValidationMessages.properties"
    @NotBlank(message = "{name.not.blank}")
    private String name;
    @Min(18)
    private Integer age;
    @Email
    private String email;
    private Status status;
    private String cep;
    private String number;

    private List<RequestDependents> dependents;

}