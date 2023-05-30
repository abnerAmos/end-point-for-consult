package com.example.demo.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@AllArgsConstructor
public class ErrorResponse {

    private final String message;
    private final int code;
    private final String status;
    private final String objectName;
    private final List<ErrorObject> errors;

    @Getter @Setter
    @AllArgsConstructor
    public static class ErrorObject {

        private final String message;
        private final String field;
        private final Object parameter;
    }
}
