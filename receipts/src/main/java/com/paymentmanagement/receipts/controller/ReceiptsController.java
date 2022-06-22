package com.paymentmanagement.receipts.controller;

import com.paymentmanagement.receipts.schemaobject.ReceiptsSo;
import com.paymentmanagement.receipts.service.ReceiptsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ReceiptsController implements  ReceiptsApi {
    private final ReceiptsService receiptsService;

    public ResponseEntity<List<ReceiptsSo>> getReceipts(String studentUniqueId) {
        return  ResponseEntity.ok(receiptsService.getReceipts(studentUniqueId));
    }
}
