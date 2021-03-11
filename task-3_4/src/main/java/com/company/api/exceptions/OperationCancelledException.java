package com.company.api.exceptions;

public class OperationCancelledException extends RuntimeException {
    public OperationCancelledException(String message) {
        super(message);
    }
}
