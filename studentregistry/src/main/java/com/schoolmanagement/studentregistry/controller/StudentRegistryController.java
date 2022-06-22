package com.schoolmanagement.studentregistry.controller;

import com.schoolmanagement.studentregistry.schemaobjects.StudentDetailsReponseObject;
import com.schoolmanagement.studentregistry.schemaobjects.StudentDetailsSchemaObject;
import com.schoolmanagement.studentregistry.service.StudentRegistryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentRegistryController {
    private final StudentRegistryService studentRegistryService;

    @PostMapping(value="/saveStudentDetails", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StudentDetailsReponseObject> saveStudentDetails(@RequestBody StudentDetailsSchemaObject body) {
        log.info("Received Add Student Details {}",body.toString());
        return ResponseEntity.ok(studentRegistryService.saveStudentDetails(body));
    }
}
