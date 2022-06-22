package com.schoolmanagement.payment.schemaobjects;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class PaymentRequestSchemaObject {
    private String studentUniqueId;
    private String cardNumber;
    private String cardType;
    private Long amount;
}
