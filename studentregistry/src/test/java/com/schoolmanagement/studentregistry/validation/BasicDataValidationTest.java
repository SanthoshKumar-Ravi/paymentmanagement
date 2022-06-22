package com.schoolmanagement.studentregistry.validation;

import com.schoolmanagement.studentregistry.exception.StudentRegistryException;
import com.schoolmanagement.studentregistry.schemaobjects.StudentDetailsSchemaObject;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BasicDataValidationTest {
    @InjectMocks
    BasicDataValidation basicDataValidation;

    @Test
    public void givenEmptyStudentDetail_whenValidating_thenThrowException(){
        try{
            StudentDetailsSchemaObject studentDetailsSchemaObject = StudentDetailsSchemaObject.builder()
                            .studentId("1")
                            .build();
            basicDataValidation.doValidation(studentDetailsSchemaObject);
        }catch(StudentRegistryException studentRegistryException){
        Assertions.assertThat(studentRegistryException)
                .isInstanceOf(StudentRegistryException.class)
                .hasMessage("ERR_DATA_EMPTY");
        }
    }

    @Test
    public void givenEmptyStudentDetail_whenValidatingTheLength_thenThrowException(){
        try{
            StudentDetailsSchemaObject studentDetailsSchemaObject = StudentDetailsSchemaObject.builder()
                    .studentId("1")
                    .studentName("A")
                    .grade("1")
                    .mobileNumber("1")
                    .schoolName("A")
                    .build();
            basicDataValidation.doValidation(studentDetailsSchemaObject);
        }catch(StudentRegistryException studentRegistryException){
            Assertions.assertThat(studentRegistryException)
                    .isInstanceOf(StudentRegistryException.class)
                    .hasMessage("ERR_LEN_VALIDATION");
        }
    }
}
