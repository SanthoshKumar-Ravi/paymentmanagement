package com.paymentmanagement.payment.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paymentmanagement.payment.service.PaymentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
public class PaymentEntryListenerTest {
    @InjectMocks
    PaymentEntryListener paymentEntryListener;
    @Mock
    ObjectMapper objectMapper;
    @Mock
    PaymentService paymentService;

    @Test
    public void whenConsumingMessage_thenPushToServiceLayer(){
        // Given
        String message = "Empty Message";
        // When
        paymentEntryListener.onMessage(message);
        // Then
        Mockito.verify(paymentService).addPaymentEntry(any());
    }
}
