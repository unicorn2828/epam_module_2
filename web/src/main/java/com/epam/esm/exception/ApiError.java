package com.epam.esm.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
public class ApiError {
    private String message;
    private String error;
    private HttpStatus status;
    private List<String> errors;

    public ApiError(HttpStatus status, String message, List<String> errors) {
        super();
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public ApiError(HttpStatus status, String message, String error) {
        super();
        this.status = status;
        this.message = message;
        this.error = error;
    }
    public ApiError(String message, String error) {
        super();
        this.message = message;
        this.error = error;
    }
}