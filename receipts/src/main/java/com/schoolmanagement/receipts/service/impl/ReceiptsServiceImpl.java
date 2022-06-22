package com.schoolmanagement.receipts.service.impl;

import com.schoolmanagement.receipts.entity.Receipts;
import com.schoolmanagement.receipts.exception.ReceiptsException;
import com.schoolmanagement.receipts.repository.ReceiptsRepository;
import com.schoolmanagement.receipts.schemaobject.PaymentResponseObject;
import com.schoolmanagement.receipts.schemaobject.ReceiptsSo;
import com.schoolmanagement.receipts.service.ReceiptsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReceiptsServiceImpl implements ReceiptsService {
    private final ReceiptsRepository receiptsRepository;

    @Override
    public List<ReceiptsSo> getReceipts(String studentUniqueId) {
        List<Receipts> receipts = receiptsRepository.findByStudentUniqueId(studentUniqueId);
        List<ReceiptsSo> receiptsSos = new ArrayList<>();
        if(!CollectionUtils.isEmpty(receipts)) {
            for (Receipts receipts1 : receipts) {
                ReceiptsSo receiptsSo = new ReceiptsSo();
                BeanUtils.copyProperties(receipts1, receiptsSo);
                receiptsSos.add(receiptsSo);
            }
        }else{
            throw new ReceiptsException("ERR_EMPTY_RECEIPTS","No Receipts available");
        }
        return receiptsSos;
    }

    @Override
    public void addReceipt(PaymentResponseObject paymentResponseObject) {
        Receipts receipts = new Receipts();
        BeanUtils.copyProperties(paymentResponseObject,receipts);
        log.info("Adding Details to receipts {}", receipts.toString());
        Receipts receiptsReturn = receiptsRepository.save(receipts);
        log.info("Successfully added receipts");
    }
}
