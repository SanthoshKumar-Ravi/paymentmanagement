package com.schoolmanagement.payment.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.schoolmanagement.payment.schemaobjects.StudentDetailsReponseObject;
import com.schoolmanagement.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class PaymentEntryListener {
    private final ObjectMapper objectMapper;
    private final PaymentService paymentService;

    @JmsListener(destination = "PAYMENT_ENTRY_QUEUE")
    public void onMessage(String message){
        try {
            log.info("Received message in a queue PAYMENT_ENTRY_QUEUE {}",message);
            StudentDetailsReponseObject studentDetailsReponseObject = objectMapper.readValue(message, StudentDetailsReponseObject.class);
            paymentService.addPaymentEntry(studentDetailsReponseObject);
        }catch (JsonProcessingException sp){
            log.error("Exception while processing the queue message",sp);
        }
    }
}
