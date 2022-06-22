package com.paymentmanagement.payment.service;

import com.paymentmanagement.payment.schemaobjects.PaymentResponseObject;
import com.paymentmanagement.payment.schemaobjects.PaymentRequestSchemaObject;
import com.paymentmanagement.payment.schemaobjects.StudentDetailsReponseObject;

public interface PaymentService {
    void addPaymentEntry(StudentDetailsReponseObject studentDetailsReponseObject);
    PaymentResponseObject doPayment(PaymentRequestSchemaObject paymentRequestSchemaObject);
}
