package com.paymentmanagement.payment.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paymentmanagement.payment.PaymentTestApplication;
import com.paymentmanagement.payment.schemaobjects.PaymentResponseObject;
import com.paymentmanagement.payment.service.PaymentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes =  PaymentTestApplication.class)
@WebMvcTest(controllers = PaymentController.class)
public class PaymentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PaymentService paymentService;

    @Test
    void givenPaymentDetails_whenProcessingPayment_thenReturnPendingDetails() throws Exception {
        PaymentResponseObject paymentResponseObject = new PaymentResponseObject();
        paymentResponseObject.setBalance(10L);
        paymentResponseObject.setPaymentReferenceNo("stringull");
        paymentResponseObject.setPaidAmount(10L);
        paymentResponseObject.setStudentId("abc");
        paymentResponseObject.setStudentName("john");
        paymentResponseObject.setCardNumber("12344656545");
        paymentResponseObject.setFeesPaid(10L);
        paymentResponseObject.setCardType("master");

        when(paymentService.doPayment(any())).thenReturn(paymentResponseObject);

        this.mockMvc.perform(post("/payment/doPayment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(paymentResponseObject)))
                .andExpect(status().isOk()).andReturn();

    }
}
