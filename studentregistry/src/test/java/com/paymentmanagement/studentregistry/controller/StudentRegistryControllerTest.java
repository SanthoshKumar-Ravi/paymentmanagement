package com.paymentmanagement.studentregistry.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paymentmanagement.studentregistry.StudentRegistryTestApplication;
import com.paymentmanagement.studentregistry.schemaobjects.StudentDetailsReponseObject;
import com.paymentmanagement.studentregistry.service.StudentRegistryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes =  StudentRegistryTestApplication.class)
@WebMvcTest(controllers = StudentRegistryController.class)
public class StudentRegistryControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private StudentRegistryService studentRegistryService;

    @Test
    void givenPaymentDetails_whenProcessingPayment_thenReturnPendingDetails() throws Exception {
        StudentDetailsReponseObject studentDetailsReponseObject = new StudentDetailsReponseObject();
        studentDetailsReponseObject.setStudentId("abc");
        studentDetailsReponseObject.setStudentName("string");
        studentDetailsReponseObject.setSchoolName("string");
        studentDetailsReponseObject.setTotalFees(1000L);
        studentDetailsReponseObject.setFeesPaid(0L);
        studentDetailsReponseObject.setGrade("A");

        when(studentRegistryService.saveStudentDetails(any())).thenReturn(studentDetailsReponseObject);

        this.mockMvc.perform(post("/student/saveStudentDetails")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(studentDetailsReponseObject)))
                .andExpect(status().isOk()).andReturn();

    }
}
