package com.paymentmanagement.receipts.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paymentmanagement.receipts.service.ReceiptsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class ReceiptListenerTest {
    @InjectMocks
    ReceiptListener receiptListener;
    @Mock
    ReceiptsService receiptsService;
    @Mock
    ObjectMapper objectMapper;

    @Test
    public void whenConsumingMessage_thenPushToServiceLayer() {
        // Given
        String message = "Empty message";
        // When
        receiptListener.onMessage(message);
        // Then
        Mockito.verify(receiptsService).addReceipt(any());
    }

}
