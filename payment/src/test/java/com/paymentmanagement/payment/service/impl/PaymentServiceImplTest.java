package com.paymentmanagement.payment.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paymentmanagement.payment.entity.PaymentDetail;
import com.paymentmanagement.payment.repository.PaymentRepository;
import com.paymentmanagement.payment.schemaobjects.PaymentRequestSchemaObject;
import com.paymentmanagement.payment.schemaobjects.PaymentResponseObject;
import com.paymentmanagement.payment.schemaobjects.StudentDetailsReponseObject;
import com.paymentmanagement.payment.service.impl.PaymentServiceImpl;
import com.paymentmanagement.payment.validation.BasicValidation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jms.core.JmsTemplate;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceImplTest {
    @InjectMocks
    PaymentServiceImpl paymentServiceImpl;
    @Mock
    PaymentRepository paymentRepository;
    @Mock
    BasicValidation basicValidation;
    @Mock
    JmsTemplate jmsTemplate;
    @Mock
    ObjectMapper objectMapper;

    @Test
    public void givenPaymentDetails_whenDataIsValid_thenSaveSuccessfully(){
        // Given
        PaymentRequestSchemaObject paymentRequestSchemaObject = getPaymentObject();
        Mockito.when(paymentRepository.findByStudentUniqueId(ArgumentMatchers.anyString())).thenReturn(fetchPaymentDetails());
        Mockito.when(paymentRepository.save(ArgumentMatchers.any())).thenReturn(savedPaymentDetailsObject());
        // When
        PaymentResponseObject paymentResponseObject = paymentServiceImpl.doPayment(paymentRequestSchemaObject);
        // Then
        Assertions.assertNotNull(paymentResponseObject);
        Assertions.assertEquals(paymentResponseObject.getCardNumber(),"12345678");
        Assertions.assertEquals(paymentResponseObject.getCardType(),"master");
        Assertions.assertEquals(paymentResponseObject.getPaidAmount(),120L);
        Assertions.assertEquals(paymentResponseObject.getStudentUniqueId(),"1234SanSRV5");
        Assertions.assertEquals(paymentResponseObject.getGrade(),"1");
        Assertions.assertEquals(paymentResponseObject.getStudentId(),"1");
        Assertions.assertEquals(paymentResponseObject.getStudentName(),"apple");
        Assertions.assertEquals(paymentResponseObject.getSchoolName(),"CVB");
        Assertions.assertEquals(paymentResponseObject.getStudentUniqueId(),"1234SanSRV5");
        Assertions.assertEquals(paymentResponseObject.getTotalFees(),1000L);
    }

    @Test
    public void givenStudentDetails_whenConsumingMessagesToQueue_thenSaveToDatabase(){
        ArgumentCaptor<PaymentDetail> paymentDetailArgumentCaptor = ArgumentCaptor.forClass(PaymentDetail.class);
        // When
        paymentServiceImpl.addPaymentEntry(getStudentDetailsResponseObject());
        // Then
        Mockito.verify(paymentRepository).save(paymentDetailArgumentCaptor.capture());
        PaymentDetail paymentDetail = paymentDetailArgumentCaptor.getValue();
        Assertions.assertEquals(paymentDetail.getStudentId(),"1");
        Assertions.assertEquals(paymentDetail.getStudentName(),"Apple");
        Assertions.assertEquals(paymentDetail.getStudentUniqueId(),"123");
        Assertions.assertEquals(paymentDetail.getGrade(),"1");
        Assertions.assertEquals(paymentDetail.getTotalFees(),1000L);
    }

    private StudentDetailsReponseObject getStudentDetailsResponseObject(){
        return StudentDetailsReponseObject.builder()
                .studentId("1")
                .studentName("Apple")
                .studentUniqueId("123")
                .grade("1")
                .totalFees(1000L)
                .build();

    }

    private PaymentRequestSchemaObject getPaymentObject(){
        return PaymentRequestSchemaObject.builder()
                .cardNumber("12345678")
                .cardType("master")
                .amount(120L)
                .studentUniqueId("1234SanSRV5")
                .build();
    }

    private PaymentDetail fetchPaymentDetails(){
        return PaymentDetail.builder()
                .grade("1")
                .studentId("1")
                .studentName("apple")
                .schoolName("CVB")
                .studentUniqueId("1234SanSRV5")
                .totalFees(1000L)
                .build();
    }

    private PaymentDetail savedPaymentDetailsObject(){
        return PaymentDetail.builder()
                .grade("1")
                .studentId("1")
                .studentName("apple")
                .schoolName("CVB")
                .studentUniqueId("1234SanSRV5")
                .totalFees(1000L)
                .cardNumber("12345678")
                .cardType("master")
                .feesPaid(120L)
                .studentUniqueId("1234SanSRV5")
                .build();
    }
}
