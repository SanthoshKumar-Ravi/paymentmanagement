package com.paymentmanagement.studentregistry.controller;

import com.paymentmanagement.studentregistry.schemaobjects.StudentDetailsReponseObject;
import com.paymentmanagement.studentregistry.schemaobjects.StudentDetailsSchemaObject;
import com.paymentmanagement.studentregistry.service.StudentRegistryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@RestController
@Slf4j
@RequiredArgsConstructor
public class StudentRegistryController implements StudentRegsitryApi{
    private final StudentRegistryService studentRegistryService;


    public ResponseEntity<StudentDetailsReponseObject> saveStudentDetails(@RequestBody StudentDetailsSchemaObject body) {
        log.info("Received Add Student Details {}",body.toString());
        return ResponseEntity.ok(studentRegistryService.saveStudentDetails(body));
    }
}
