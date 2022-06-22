package com.paymentmanagement.studentregistry.repository;

import com.paymentmanagement.studentregistry.entity.StudentDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentDetailsRepository extends CrudRepository<StudentDetails, Long> {
    StudentDetails findByStudentUniqueId(String studentUniqueId);
}
