package com.paymentmanagement.receipts.repository;

import com.paymentmanagement.receipts.entity.Receipts;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceiptsRepository extends CrudRepository<Receipts, Long> {
    List<Receipts> findByStudentUniqueId(String studentUniqueId);
}
