package com.schoolmanagement.studentregistry.validation;

import com.schoolmanagement.studentregistry.exception.StudentRegistryException;
import com.schoolmanagement.studentregistry.schemaobjects.StudentDetailsSchemaObject;
import org.h2.util.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class BasicDataValidation {

    public void doValidation(StudentDetailsSchemaObject studentDetailsSchemaObject){
        emptyCheckValidation(studentDetailsSchemaObject);
    }

    private void emptyCheckValidation(StudentDetailsSchemaObject studentDetailsSchemaObject) {
        if(StringUtils.isNullOrEmpty(studentDetailsSchemaObject.getStudentId()) ||
                StringUtils.isNullOrEmpty(studentDetailsSchemaObject.getStudentName()) ||
                StringUtils.isNullOrEmpty(studentDetailsSchemaObject.getGrade()) ||
                StringUtils.isNullOrEmpty(studentDetailsSchemaObject.getMobileNumber()) ||
                StringUtils.isNullOrEmpty(studentDetailsSchemaObject.getSchoolName())) {
            throw new StudentRegistryException("ERR_DATA_EMPTY","Please provide values for all the fields");
        }
    }

    private void checkDataLength(StudentDetailsSchemaObject studentDetailsSchemaObject) {
        if(studentDetailsSchemaObject.getStudentName().length() >= 3){
            throw new StudentRegistryException("ERR_LEN_VALIDATION","Student name length should be greater than 3");
        }
        if(studentDetailsSchemaObject.getSchoolName().length() >= 3){
            throw new StudentRegistryException("ERR_LEN_VALIDATION","School name length should be greater than 3");
        }
    }
}

