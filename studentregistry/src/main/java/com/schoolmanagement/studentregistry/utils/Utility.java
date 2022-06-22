package com.schoolmanagement.studentregistry.utils;

import com.schoolmanagement.studentregistry.schemaobjects.StudentDetailsSchemaObject;

public class Utility {
    public static String generateId(StudentDetailsSchemaObject studentDetailsSchemaObject){
        String uniqueId = studentDetailsSchemaObject.getStudentId()+
                studentDetailsSchemaObject.getStudentName().substring(0,3)+
                studentDetailsSchemaObject.getSchoolName().substring(0,3)+
                studentDetailsSchemaObject.getGrade();
        return uniqueId;
    }
}
