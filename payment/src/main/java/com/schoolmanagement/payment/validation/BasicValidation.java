package com.schoolmanagement.payment.validation;

import com.schoolmanagement.payment.exception.PaymentsException;
import com.schoolmanagement.payment.schemaobjects.PaymentRequestSchemaObject;
import org.h2.util.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class BasicValidation {
    public void doBasicValidation(PaymentRequestSchemaObject paymentRequestSchemaObject){
        if(StringUtils.isNullOrEmpty(paymentRequestSchemaObject.getStudentUniqueId()) ||
                StringUtils.isNullOrEmpty(paymentRequestSchemaObject.getCardNumber()) ||
                StringUtils.isNullOrEmpty(paymentRequestSchemaObject.getCardType())){
            throw new PaymentsException("ERR_EMPTY_DATA", "Please Enter card number and card Type");
        }
        if(paymentRequestSchemaObject.getAmount()==null || paymentRequestSchemaObject.getAmount()<=0){
            throw new PaymentsException("ERR_EMPTY_AMOUNT", "Please Enter valid amount");
        }
    }
}
