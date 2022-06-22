package com.paymentmanagement.studentregistry.exception;

import lombok.Data;
import org.springframework.validation.Errors;

@Data
public class StudentRegistryException extends RuntimeException {
    private Errors errors;
    private String errorCode;
    private String errorMessage;

    public StudentRegistryException(String errorCode, String message) {
        super(errorCode);
        this.setErrorCode(errorCode);
        this.setErrorMessage(message);
        this.setErrors(errors);
    }
}
