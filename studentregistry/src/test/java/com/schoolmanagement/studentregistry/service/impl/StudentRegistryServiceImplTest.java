package com.schoolmanagement.studentregistry.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.schoolmanagement.studentregistry.entity.StudentDetails;
import com.schoolmanagement.studentregistry.repository.StudentDetailsRepository;
import com.schoolmanagement.studentregistry.schemaobjects.StudentDetailsSchemaObject;
import com.schoolmanagement.studentregistry.validation.BasicDataValidation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jms.core.JmsTemplate;

@ExtendWith(MockitoExtension.class)
public class StudentRegistryServiceImplTest {
    @InjectMocks
    StudentRegistryServiceImpl studentRegistryServiceImpl;
    @Mock
    StudentDetailsRepository studentDetailsRepository;
    @Mock
    BasicDataValidation basicDataValidation;
    @Mock
    JmsTemplate jmsTemplate;
    @Mock
    ObjectMapper objectMapper;

    @Test
    public void givenRequestObject_whenProcessingData_thenSaveTheStudentDetails(){
        Mockito.when(studentDetailsRepository.save(ArgumentMatchers.any())).thenReturn(getStudentDetails());
        // When
        studentRegistryServiceImpl.saveStudentDetails(getStudentDetailsSchemaObject());
        // Then
        ArgumentCaptor<StudentDetails> studentDetailsArgumentCaptor = ArgumentCaptor.forClass(StudentDetails.class);
        Mockito.verify(studentDetailsRepository).save(studentDetailsArgumentCaptor.capture());
        StudentDetails studentDetails = studentDetailsArgumentCaptor.getValue();
        Assertions.assertEquals(studentDetails.getStudentUniqueId(),"12AppABC2");
        Assertions.assertEquals(studentDetails.getStudentId(),"12");
        Assertions.assertEquals(studentDetails.getStudentName(),"Apple");
        Assertions.assertEquals(studentDetails.getGrade(),"2");
        Assertions.assertEquals(studentDetails.getMobileNumber(),"12345678");
        Assertions.assertEquals(studentDetails.getSchoolName(),"ABC");
    }

    private StudentDetailsSchemaObject getStudentDetailsSchemaObject(){
        return StudentDetailsSchemaObject.builder()
                .studentId("12")
                .studentName("Apple")
                .grade("2")
                .mobileNumber("12345678")
                .schoolName("ABC")
                .build();
    }

    private StudentDetails getStudentDetails(){
        return StudentDetails.builder()
                .studentId("12")
                .studentName("Apple")
                .grade("2")
                .mobileNumber("12345678")
                .schoolName("ABC")
                .build();
    }

}
