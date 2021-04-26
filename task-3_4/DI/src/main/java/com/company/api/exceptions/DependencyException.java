package com.company.api.exceptions;

public class DependencyException extends RuntimeException {
    public DependencyException(String message) {
        super(message);
    }
}
