package com.paymentmanagement.receipts.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.paymentmanagement.receipts.schemaobject.PaymentResponseObject;
import com.paymentmanagement.receipts.service.ReceiptsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class ReceiptListener {

    private final ObjectMapper objectMapper;
    private final ReceiptsService receiptsService;
    @JmsListener(destination = "RECEIPT_ENTRY_QUEUE")
    public void onMessage(String message){
        try {
            log.info("Received message in a queue PAYMENT_ENTRY_QUEUE {}",message);
            PaymentResponseObject studentDetailsReponseObject = objectMapper.readValue(message, PaymentResponseObject.class);
            receiptsService.addReceipt(studentDetailsReponseObject);
        }catch (JsonProcessingException sp){
            log.error("Exception while processing the queue message",sp);
        }
    }
}
