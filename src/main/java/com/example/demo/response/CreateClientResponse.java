package com.example.demo.response;

import com.example.demo.enums.Status;
import com.example.demo.model.Address;
import com.example.demo.model.Dependents;
import lombok.Data;

import java.util.List;

@Data
public class CreateClientResponse {

    private String name;
    private Integer age;
    private String email;
    private Status status;
    private List<Dependents> dependents;
    private Address address;
}
