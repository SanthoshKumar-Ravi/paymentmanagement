package com.schoolmanagement.receipts.service;

import com.schoolmanagement.receipts.schemaobject.PaymentResponseObject;
import com.schoolmanagement.receipts.schemaobject.ReceiptsSo;

import java.util.List;

public interface ReceiptsService {
    List<ReceiptsSo> getReceipts(String studentUniqueId);
    void addReceipt(PaymentResponseObject studentDetailsReponseObject);
}
