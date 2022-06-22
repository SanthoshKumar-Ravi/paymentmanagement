package com.paymentmanagement.payment.mapper;

import com.paymentmanagement.payment.entity.PaymentDetail;
import com.paymentmanagement.payment.schemaobjects.PaymentResponseObject;
import org.springframework.beans.BeanUtils;

public class PaymentMapper {

    public static PaymentResponseObject mapPaymentEntityToSchemaObject(PaymentDetail paymentDetail){
        PaymentResponseObject paymentResponseObject = new PaymentResponseObject();
        BeanUtils.copyProperties(paymentDetail, paymentResponseObject);
        paymentResponseObject.setBalance(paymentDetail.getBalance());
        paymentResponseObject.setPaymentReferenceNo(paymentDetail.getTransactionId());
        paymentResponseObject.setDateOfPayment(paymentDetail.getDateOfPayment());
        paymentResponseObject.setPaidAmount(paymentDetail.getFeesPaid());
        return paymentResponseObject;
    }
}
