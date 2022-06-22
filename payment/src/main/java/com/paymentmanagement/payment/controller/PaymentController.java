package com.paymentmanagement.payment.controller;

import com.paymentmanagement.payment.schemaobjects.PaymentRequestSchemaObject;
import com.paymentmanagement.payment.schemaobjects.PaymentResponseObject;
import com.paymentmanagement.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class PaymentController implements PaymentApi {
    private final PaymentService paymentService;

    public ResponseEntity<PaymentResponseObject> doPayment(@RequestBody PaymentRequestSchemaObject paymentRequestSchemaObject){
        log.info("Received Payment Details {}",paymentRequestSchemaObject.toString());
        return ResponseEntity.ok(paymentService.doPayment(paymentRequestSchemaObject));
    }
}
