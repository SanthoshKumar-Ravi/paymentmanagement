package com.paymentmanagement.receipts.exception;

import lombok.Data;
import org.springframework.validation.Errors;

@Data
public class ReceiptsException extends RuntimeException {
    private Errors errors;
    private String errorCode;
    private String errorMessage;

    public ReceiptsException(String errorCode, String message) {
        super(errorCode);
        this.setErrorCode(errorCode);
        this.setErrorMessage(message);
        this.setErrors(errors);
    }
}
