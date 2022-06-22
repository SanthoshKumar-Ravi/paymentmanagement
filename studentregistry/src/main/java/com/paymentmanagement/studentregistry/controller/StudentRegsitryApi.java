package com.paymentmanagement.studentregistry.controller;

import com.paymentmanagement.studentregistry.schemaobjects.StudentDetailsReponseObject;
import com.paymentmanagement.studentregistry.schemaobjects.StudentDetailsSchemaObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Api(value="/student")
@RequestMapping("/student")
public interface StudentRegsitryApi {

    @ApiOperation(value = "Student Details Regsitry", nickname = "Payment", notes = "Student Registry API")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Student Successfully added in the registry"),
            @ApiResponse(code = 404, message = "No data found")
    })
    @PostMapping(value = "/saveStudentDetails", produces =  MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<StudentDetailsReponseObject> saveStudentDetails(@Valid StudentDetailsSchemaObject studentDetailsSchemaObject);
}
