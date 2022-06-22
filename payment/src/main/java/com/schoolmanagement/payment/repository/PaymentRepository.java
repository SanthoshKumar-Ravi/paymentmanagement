package com.schoolmanagement.payment.repository;

import com.schoolmanagement.payment.entity.PaymentDetail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends CrudRepository<PaymentDetail, Long> {
    PaymentDetail findByStudentUniqueId(String studentUniqueId);
}
