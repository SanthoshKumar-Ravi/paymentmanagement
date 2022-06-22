package com.paymentmanagement.receipts.schemaobject;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class PaymentResponseObject {
    private String paymentReferenceNo;
    private String dateOfPayment;
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
    private String transactionId;
    private String studentUniqueId;
}
