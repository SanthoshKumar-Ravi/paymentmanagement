package com.schoolmanagement.studentregistry.schemaobjects;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@Builder
public class StudentDetailsSchemaObject {
    private String studentId;
    private String studentName;
    private String grade;
    private String mobileNumber;
    private String schoolName;
}
