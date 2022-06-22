package com.paymentmanagement.payment.controller;

import com.paymentmanagement.payment.schemaobjects.PaymentRequestSchemaObject;
import com.paymentmanagement.payment.schemaobjects.PaymentResponseObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Api(value="/payment")
@RequestMapping("/payment")
public interface PaymentApi {

    @ApiOperation(value = "Enter details for payment", nickname = "Payment", notes = "Payment API")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "payment successfully"),
            @ApiResponse(code = 404, message = "No data found")
    })
    @PostMapping(value = "/doPayment", produces =  MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<PaymentResponseObject> doPayment(@Valid PaymentRequestSchemaObject paymentRequestSchemaObject);
}
