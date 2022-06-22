package com.schoolmanagement.receipts.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Receipts {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
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
