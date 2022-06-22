package com.schoolmanagement.payment.service;

import com.schoolmanagement.payment.schemaobjects.PaymentResponseObject;
import com.schoolmanagement.payment.schemaobjects.PaymentRequestSchemaObject;
import com.schoolmanagement.payment.schemaobjects.PaymentResponseObject;
import com.schoolmanagement.payment.schemaobjects.StudentDetailsReponseObject;

public interface PaymentService {
    void addPaymentEntry(StudentDetailsReponseObject studentDetailsReponseObject);
    PaymentResponseObject doPayment(PaymentRequestSchemaObject paymentRequestSchemaObject);
}
