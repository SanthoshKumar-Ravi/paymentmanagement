package com.schoolmanagement.studentregistry.service;

import com.schoolmanagement.studentregistry.schemaobjects.StudentDetailsReponseObject;
import com.schoolmanagement.studentregistry.schemaobjects.StudentDetailsSchemaObject;

public interface StudentRegistryService {
    StudentDetailsReponseObject saveStudentDetails(StudentDetailsSchemaObject studentDetailsSchemaObject);
}
