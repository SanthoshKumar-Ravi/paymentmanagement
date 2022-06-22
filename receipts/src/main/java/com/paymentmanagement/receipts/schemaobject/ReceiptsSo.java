package com.paymentmanagement.receipts.schemaobject;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@Builder
public class ReceiptsSo {
    private Long receiptsId;
    private String studentId;
    private String studentName;
    private String grade;
    private String schoolName;
    private Long totalFees;
    private Long feesPaid;
    private Long balance;
    private String cardNumber;
    private String cardType;
    private String dateOfPayment;
    private String transactionId;
    private String studentUniqueId;
}
