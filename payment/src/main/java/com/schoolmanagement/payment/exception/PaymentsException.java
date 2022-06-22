package com.schoolmanagement.payment.exception;

import lombok.Data;
import org.springframework.validation.Errors;

@Data
public class PaymentsException extends RuntimeException {
    private Errors errors;
    private String errorCode;
    private String errorMessage;

    public PaymentsException(String errorCode, String message) {
        super(errorCode);
        this.setErrorCode(errorCode);
        this.setErrorMessage(message);
        this.setErrors(errors);
    }
}
