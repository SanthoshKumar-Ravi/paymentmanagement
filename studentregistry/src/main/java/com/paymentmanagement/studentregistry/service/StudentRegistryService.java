package com.paymentmanagement.studentregistry.service;

import com.paymentmanagement.studentregistry.schemaobjects.StudentDetailsReponseObject;
import com.paymentmanagement.studentregistry.schemaobjects.StudentDetailsSchemaObject;

public interface StudentRegistryService {
    StudentDetailsReponseObject saveStudentDetails(StudentDetailsSchemaObject studentDetailsSchemaObject);
}
