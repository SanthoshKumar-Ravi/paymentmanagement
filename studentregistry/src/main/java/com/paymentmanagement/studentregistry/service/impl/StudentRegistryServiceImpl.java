package com.paymentmanagement.studentregistry.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.paymentmanagement.studentregistry.validation.BasicDataValidation;
import com.paymentmanagement.studentregistry.entity.StudentDetails;
import com.paymentmanagement.studentregistry.exception.StudentRegistryException;
import com.paymentmanagement.studentregistry.repository.StudentDetailsRepository;
import com.paymentmanagement.studentregistry.schemaobjects.StudentDetailsReponseObject;
import com.paymentmanagement.studentregistry.schemaobjects.StudentDetailsSchemaObject;
import com.paymentmanagement.studentregistry.service.StudentRegistryService;
import com.paymentmanagement.studentregistry.utils.Utility;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
@Slf4j
@RequiredArgsConstructor
public class StudentRegistryServiceImpl implements StudentRegistryService {
    private final StudentDetailsRepository studentDetailsRepository;
    private final BasicDataValidation basicDataValidation;
    private final JmsTemplate jmsTemplate;
    private final ObjectMapper objectMapper;

    private static final Long AMOUNT_ZERO= 0L;
    private static final Long TOTAL_FEES= 1000L;
    private static final String PAYMENT_ENTRY_QUEUE= "PAYMENT_ENTRY_QUEUE";

    @Override
    public StudentDetailsReponseObject saveStudentDetails(StudentDetailsSchemaObject studentDetailsSchemaObject) {
        StudentDetails studentDetails;
        basicDataValidation.doValidation(studentDetailsSchemaObject);
        String studentUniqueId = Utility.generateId(studentDetailsSchemaObject);
        studentDetails = studentDetailsRepository.findByStudentUniqueId(studentUniqueId);
        studentDetails = !ObjectUtils.isEmpty(studentDetails) ? studentDetails : new StudentDetails();
        studentDetails.setStudentUniqueId(Utility.generateId(studentDetailsSchemaObject));
        BeanUtils.copyProperties(studentDetailsSchemaObject, studentDetails);
        studentDetails.setTotalFees(TOTAL_FEES);
        studentDetails.setFeesPaid(AMOUNT_ZERO);

        StudentDetails returnStudentDetails = studentDetailsRepository.save(studentDetails);

        StudentDetailsReponseObject studentDetailsReponseObject = StudentDetailsReponseObject
                .builder()
                .studentUniqueId(returnStudentDetails.getStudentUniqueId())
                .feesPaid(AMOUNT_ZERO)
                .totalFees(returnStudentDetails.getTotalFees())
                .studentId(returnStudentDetails.getStudentId())
                .studentName(returnStudentDetails.getStudentName())
                .grade(returnStudentDetails.getGrade())
                .schoolName(returnStudentDetails.getSchoolName())
                .notes("Please Note the student id for future use")
                .build();
        try {
            jmsTemplate.convertAndSend(PAYMENT_ENTRY_QUEUE, objectMapper.writeValueAsString(studentDetailsReponseObject));
        } catch (JsonProcessingException e) {
            throw new StudentRegistryException("ERR_EXP_PARSING", "Bad data");
        }
        log.info("response data {}",studentDetailsReponseObject.toString());
        return studentDetailsReponseObject;
    }
}
