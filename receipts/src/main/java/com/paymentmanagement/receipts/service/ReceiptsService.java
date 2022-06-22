package com.paymentmanagement.receipts.service;

import com.paymentmanagement.receipts.schemaobject.PaymentResponseObject;
import com.paymentmanagement.receipts.schemaobject.ReceiptsSo;

import java.util.List;

public interface ReceiptsService {
    List<ReceiptsSo> getReceipts(String studentUniqueId);
    void addReceipt(PaymentResponseObject studentDetailsReponseObject);
}
