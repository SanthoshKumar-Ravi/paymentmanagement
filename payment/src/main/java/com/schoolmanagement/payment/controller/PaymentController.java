package com.schoolmanagement.payment.controller;

import com.schoolmanagement.payment.schemaobjects.PaymentRequestSchemaObject;
import com.schoolmanagement.payment.schemaobjects.PaymentResponseObject;
import com.schoolmanagement.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/payment")
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping(value="/doPayment", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PaymentResponseObject> doPayment(@RequestBody PaymentRequestSchemaObject paymentRequestSchemaObject){
        log.info("Received Payment Details {}",paymentRequestSchemaObject.toString());
        return ResponseEntity.ok(paymentService.doPayment(paymentRequestSchemaObject));
    }
}
