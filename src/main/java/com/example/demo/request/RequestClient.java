package com.example.demo.request;

import com.example.demo.enums.Status;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class RequestClient implements Serializable {

    private String name;
    private Integer age;
    private String email;
    private Status status;

    private String name_dp;
    private Integer age_dp;
}
