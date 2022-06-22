package com.schoolmanagement.receipts.service.impl;

import com.schoolmanagement.receipts.entity.Receipts;
import com.schoolmanagement.receipts.exception.ReceiptsException;
import com.schoolmanagement.receipts.repository.ReceiptsRepository;
import com.schoolmanagement.receipts.schemaobject.PaymentResponseObject;
import com.schoolmanagement.receipts.schemaobject.ReceiptsSo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ReceiptsServiceImplTest {
    @InjectMocks
    ReceiptsServiceImpl receiptsServiceImpl;
    @Mock
    ReceiptsRepository receiptsRepository;

    @Test
    public void givenPaymentDetails_whenProcessingTheData_thenSaveTheReceipts(){
        receiptsServiceImpl.addReceipt(getPaymentResponseObject());
        // Then
        ArgumentCaptor<Receipts> receiptsArgumentCaptor = ArgumentCaptor.forClass(Receipts.class);
        Mockito.verify(receiptsRepository).save(receiptsArgumentCaptor.capture());
        Receipts receipts = receiptsArgumentCaptor.getValue();
        Assertions.assertEquals(receipts.getBalance(),30L);
        Assertions.assertEquals(receipts.getCardNumber(),"12345");
        Assertions.assertEquals(receipts.getCardType(),"master");
        Assertions.assertEquals(receipts.getGrade(),"1");
        Assertions.assertEquals(receipts.getFeesPaid(),100L);
        Assertions.assertEquals(receipts.getFeesPaid(),100L);
        Assertions.assertEquals(receipts.getStudentId(),"1");
        Assertions.assertEquals(receipts.getStudentName(),"Apple");
        Assertions.assertEquals(receipts.getStudentUniqueId(),"1SAN");
    }

    @Test
    public void givenStudentUniqueId_whenFetchingData_thenReturnTheReceipts(){
        Mockito.when(receiptsRepository.findByStudentUniqueId(ArgumentMatchers.anyString())).thenReturn(Arrays.asList(getReceiptDetails()));
        // When
        List<ReceiptsSo> receiptsList = receiptsServiceImpl.getReceipts(ArgumentMatchers.anyString());
        // Then
        Assertions.assertNotNull(receiptsList);
        ReceiptsSo receiptsSo = receiptsList.get(0);
        Assertions.assertEquals(receiptsSo.getBalance(),30L);
        Assertions.assertEquals(receiptsSo.getCardNumber(),"12345");
        Assertions.assertEquals(receiptsSo.getCardType(),"master");
        Assertions.assertEquals(receiptsSo.getFeesPaid(),100L);
        Assertions.assertEquals(receiptsSo.getReceiptsId(),1L);
        Assertions.assertEquals(receiptsSo.getGrade(),"1");
        Assertions.assertEquals(receiptsSo.getStudentId(),"1");
        Assertions.assertEquals(receiptsSo.getStudentName(),"Apple");
        Assertions.assertEquals(receiptsSo.getStudentUniqueId(),"1SAN");
        Assertions.assertEquals(receiptsSo.getTotalFees(),200L);
    }

    @Test
    public void givenInvalidUniqueId_whenFetchingTheData_thenThrowException(){
        Mockito.when(receiptsRepository.findByStudentUniqueId(ArgumentMatchers.anyString())).thenReturn(Collections.emptyList());
        try{
            receiptsServiceImpl.getReceipts(ArgumentMatchers.anyString());
        }catch(ReceiptsException receiptsException){
            org.assertj.core.api.Assertions.assertThat(receiptsException)
                    .isInstanceOf(ReceiptsException.class)
                    .hasMessage("ERR_EMPTY_RECEIPTS");
        }
    }

    private PaymentResponseObject getPaymentResponseObject(){
        return PaymentResponseObject.builder()
                .paymentReferenceNo("1234")
                .balance(30L)
                .cardNumber("12345")
                .cardType("master")
                .grade("1")
                .feesPaid(100L)
                .studentId("1")
                .studentName("Apple")
                .studentUniqueId("1SAN")
                .totalFees(200L)
                .build();
    }

    private Receipts getReceiptDetails(){
        return Receipts.builder()
                .receiptsId(1L)
                .balance(30L)
                .cardNumber("12345")
                .cardType("master")
                .grade("1")
                .feesPaid(100L)
                .studentId("1")
                .studentName("Apple")
                .studentUniqueId("1SAN")
                .totalFees(200L)
                .build();
    }


}
