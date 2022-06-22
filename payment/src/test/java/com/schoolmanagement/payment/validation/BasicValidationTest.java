package com.schoolmanagement.payment.validation;

import com.schoolmanagement.payment.schemaobjects.PaymentRequestSchemaObject;
import com.schoolmanagement.payment.exception.PaymentsException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BasicValidationTest {
    @InjectMocks
    BasicValidation basicValidation;

    @Test
    public void givenEmptyPaymentRequest_whenValidating_thenThrowException(){
        try{
            basicValidation.doBasicValidation(new PaymentRequestSchemaObject());
        }catch(PaymentsException paymentsException){
            Assertions.assertThat(paymentsException)
                    .isInstanceOf(PaymentsException.class)
                    .hasMessage("ERR_EMPTY_DATA");
        }
    }

    @Test
    public void givenPaymentRequestWithZeroAmount_whenValidating_thenThrowException(){
        try{
            basicValidation.doBasicValidation(new PaymentRequestSchemaObject("123","123","123",0L));
        }catch(PaymentsException paymentsException){
            Assertions.assertThat(paymentsException)
                    .isInstanceOf(PaymentsException.class)
                    .hasMessage("ERR_EMPTY_AMOUNT");
        }
    }
}
