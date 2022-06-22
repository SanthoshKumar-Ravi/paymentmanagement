package com.paymentmanagement.studentregistry.schemaobjects;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@Builder
public class StudentDetailsReponseObject {
    private String studentUniqueId;
    private String notes;
    private Long totalFees;
    private Long feesPaid;
    private String schoolName;
    private String grade;
    private String studentId;
    private String studentName;
}
