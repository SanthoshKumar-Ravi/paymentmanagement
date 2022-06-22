package com.paymentmanagement.payment.schemaobjects;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@Builder
public class PaymentResponseObject {
    private String paymentReferenceNo;
    private String date;
    private Long paidAmount;
    private Long balance;
    private String studentId;
    private String studentName;
    private String grade;
    private String mobileNumber;
    private String schoolName;
    private Long totalFees;
    private Long feesPaid;
    private String cardNumber;
    private String cardType;
    private String dateOfPayment;
    private String transactionId;
    private String studentUniqueId;
}
