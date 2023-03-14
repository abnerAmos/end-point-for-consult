package com.example.demo.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class RequestDependents implements Serializable {
        private String name_dp;
        private Integer age_dp;

}