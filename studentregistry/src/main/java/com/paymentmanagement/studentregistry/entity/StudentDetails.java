package com.paymentmanagement.studentregistry.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentDetails {
    @Id
    private String studentUniqueId;
    private String studentId;
    private String studentName;
    private String grade;
    private String mobileNumber;
    private String schoolName;
    private Long totalFees;
    private Long feesPaid;
}